package com.zerobase.schedulemanagement.domain.entity;

import com.zerobase.schedulemanagement.entry.dto.schedule.UpdateScheduleParamDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Table(name = "schedule")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Schedule {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String description;

  @Column(nullable = false)
  private Long startAt;

  @Column(nullable = false)
  private Long endAt;

  @Column(nullable = false)
  private Boolean isDone;

  @CreatedDate
  @Column(updatable = false)
  private LocalDateTime createdAt;

  @LastModifiedDate
  private LocalDateTime updatedAt;

  @Column(updatable = false)
  private Long createdBy;

  private Long updatedBy;

  public void apply(UpdateScheduleParamDto dto){
    this.title = dto.getTitle();
    this.description = dto.getDescription();
    this.startAt = dto.getStartAt();
    this.endAt = dto.getEndAt();
    this.updatedBy = dto.getMemberId();
  }
}
