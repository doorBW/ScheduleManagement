package com.zerobase.schedulemanagement.infra.exception;

import com.zerobase.schedulemanagement.entry.dto.ResponseCode;
import lombok.Getter;

@Getter
public class ScheduleManagementException extends RuntimeException {

  private ResponseCode code;
  private String message;

  public ScheduleManagementException(ResponseCode responseCode) {
    this.code = responseCode;
    this.message = responseCode.getMessage();
  }

  public ScheduleManagementException(ResponseCode returnCode, String returnMessage) {
    this.code = returnCode;
    this.message = returnMessage;
  }
}
