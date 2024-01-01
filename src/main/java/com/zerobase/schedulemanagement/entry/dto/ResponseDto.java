package com.zerobase.schedulemanagement.entry.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.Optional;
import lombok.Getter;
import lombok.ToString;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

@Getter
@JsonInclude(Include.NON_NULL) // data가 null인 경우 response에 포함시키지 않음
@ToString
public class ResponseDto<T> {

  private String code;
  private String message;
  private T data;

  public static <T> ResponseDto of(T data) {
    ResponseDto<T> response = new ResponseDto<>();
    response.code = ResponseCode.SUCCESS.getCode();
    response.message = ResponseCode.SUCCESS.getMessage();
    response.data = data;

    return response;
  }

  public static <T> ResponseDto of(ResponseCode responseCode) {
    ResponseDto<T> response = new ResponseDto<>();
    response.code = responseCode.getCode();
    response.message = responseCode.getMessage();

    return response;
  }

  public static <T> ResponseDto of(ResponseCode responseCode, String returnMessage) {
    ResponseDto<T> response = new ResponseDto<>();
    response.code = responseCode.getCode();
    response.message = returnMessage;

    return response;
  }

  public static <T> ResponseDto of(ResponseCode responseCode, BindingResult errors) {
    ResponseDto<T> response = new ResponseDto<>();
    response.code = responseCode.getCode();

    response.message = Optional.ofNullable(errors)
                               .map(BindingResult::getAllErrors)
                               .flatMap(errorsList -> errorsList.stream().findFirst())
                               .map(ObjectError::getDefaultMessage)
                               .orElse(null);

    return response;
  }
}
