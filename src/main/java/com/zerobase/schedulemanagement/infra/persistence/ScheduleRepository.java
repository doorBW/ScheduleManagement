package com.zerobase.schedulemanagement.infra.persistence;

import com.zerobase.schedulemanagement.domain.entity.Schedule;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
  List<Schedule> findAllByIdIn(List<Long> ids);
}
