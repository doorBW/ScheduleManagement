package com.zerobase.schedulemanagement.entry.dto.schedule;

import com.zerobase.schedulemanagement.domain.entity.Schedule;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateScheduleParamDto {
  private Long memberId;
  private String title;
  private String description;
  private Long startAt;
  private Long endAt;
  private List<Long> participationIds;

  public Schedule toEntity(Long memberId) {
    return Schedule.builder()
                   .title(this.title)
                   .description(this.description)
                   .startAt(this.startAt)
                   .endAt(this.endAt)
                   .isDone(false)
                   .createdBy(memberId)
                   .updatedBy(memberId)
                   .build();
  }
}
