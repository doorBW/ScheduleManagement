package com.zerobase.schedulemanagement.entry.dto.schedule;

import java.util.List;
import lombok.Data;

@Data
public class UpdateScheduleRequestDto {

  private String title;
  private String description;
  private Long startAt;
  private Long endAt;
  private List<Long> participationIds;

  public UpdateScheduleParamDto toParam(Long scheduleId, Long memberId) {
    return UpdateScheduleParamDto.builder()
                                 .scheduleId(scheduleId)
                                 .memberId(memberId)
                                 .title(this.getTitle())
                                 .description(this.getDescription())
                                 .startAt(this.getStartAt())
                                 .endAt(this.getEndAt())
                                 .participationIds(this.getParticipationIds())
                                 .build();
  }
}
