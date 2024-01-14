package com.zerobase.schedulemanagement.entry.dto.schedule;

import com.zerobase.schedulemanagement.domain.entity.Schedule;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateScheduleParamDto {
  private Long scheduleId;
  private Long memberId;
  private String title;
  private String description;
  private Long startAt;
  private Long endAt;
  private List<Long> participationIds;
}
