package com.ajmanlove.spring.structs;

public class RestErrorResponse {

  public Integer status;
  public String message;

  public Integer getStatus() {
    return status;
  }

  public RestErrorResponse setStatus(Integer status) {
    this.status = status;
    return this;
  }

  public String getMessage() {
    return message;
  }

  public RestErrorResponse setMessage(String message) {
    this.message = message;
    return this;
  }
}
