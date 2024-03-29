package com.zerobase.schedulemanagement.entry.dto.schedule;

import com.zerobase.schedulemanagement.domain.entity.Schedule;
import java.util.List;
import lombok.Data;

@Data
public class CreateScheduleRequestDto {

  private String title;
  private String description;
  private Long startAt;
  private Long endAt;
  private List<Long> participationIds;

  public CreateScheduleParamDto toParam(Long memberId){
    return CreateScheduleParamDto.builder()
                                 .memberId(memberId)
                                 .title(this.getTitle())
                                 .description(this.getDescription())
                                 .startAt(this.getStartAt())
                                 .endAt(this.getEndAt())
                                 .participationIds(this.getParticipationIds())
                                 .build();
  }
}
