package com.zerobase.schedulemanagement.entry.dto.schedule;

import com.zerobase.schedulemanagement.domain.entity.Schedule;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateScheduleParamDto {

  private Schedule schedule;
  private List<Long> participationIds;

}
