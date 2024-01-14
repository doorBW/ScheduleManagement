package com.zerobase.schedulemanagement.domain.service;

import com.zerobase.schedulemanagement.domain.entity.Member;
import com.zerobase.schedulemanagement.domain.entity.MemberSchedule;
import com.zerobase.schedulemanagement.domain.entity.Schedule;
import com.zerobase.schedulemanagement.entry.dto.ResponseCode;
import com.zerobase.schedulemanagement.entry.dto.schedule.CreateScheduleParamDto;
import com.zerobase.schedulemanagement.entry.dto.schedule.ScheduleResponseDto;
import com.zerobase.schedulemanagement.infra.exception.ScheduleManagementException;
import com.zerobase.schedulemanagement.infra.persistence.MemberRepository;
import com.zerobase.schedulemanagement.infra.persistence.MemberScheduleRepository;
import com.zerobase.schedulemanagement.infra.persistence.ScheduleRepository;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleService {

  private final ScheduleRepository scheduleRepository;
  private final MemberScheduleRepository memberScheduleRepository;
  private final MemberRepository memberRepository;

  public List<Schedule> getSchedules(Long memberId) {
    List<MemberSchedule> memberSchedules = memberScheduleRepository.findAllByMemberId(memberId);
    return scheduleRepository.findAllByIdIn(memberSchedules.stream()
                                                           .map(memberSchedule -> memberSchedule.getSchedule().getId())
                                                           .collect(Collectors.toList()));
  }

  public ScheduleResponseDto getSchedule(Long scheduleId, Long memberId) {
    Schedule schedule = scheduleRepository.findById(scheduleId)
                                          .orElseThrow(() -> new ScheduleManagementException(ResponseCode.NO_SCHEDULE));

    List<MemberSchedule> memberSchedules = memberScheduleRepository.findAllByScheduleId(scheduleId);
    List<Long> participationIds = memberSchedules.stream().map(memberSchedule -> memberSchedule.getMember().getId())
                                                 .toList();
    if (!participationIds.contains(memberId)) {
      throw new ScheduleManagementException(ResponseCode.NOT_PARTICIPATED_SCHEDULE);
    }

    return ScheduleResponseDto.from(schedule, participationIds);
  }

  public Long createSchedule(CreateScheduleParamDto dto) {
    // member check
    List<Long> participationId = dto.getParticipationIds();
    List<Member> members = memberRepository.findAllByIdIn(participationId);
    if (members.size() != participationId.size()) {
      throw new ScheduleManagementException(ResponseCode.NO_MEMBER);
    }

    // save schedule
    Schedule schedule = scheduleRepository.save(dto.getSchedule());

    // save memberSchedule
    List<MemberSchedule> participations = new java.util.ArrayList<>(Collections.emptyList());
    members.forEach(member -> {
      MemberSchedule memberSchedule = MemberSchedule.builder()
                                                    .member(member)
                                                    .schedule(schedule)
                                                    .build();
      participations.add(memberSchedule);
    });

    memberScheduleRepository.saveAll(participations);
    return schedule.getId();
  }
}
