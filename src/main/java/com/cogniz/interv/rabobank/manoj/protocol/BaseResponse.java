package com.cogniz.interv.rabobank.manoj.protocol;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class BaseResponse
{

  @JsonProperty("result")
  String result;

  @JsonProperty("error")
  @JsonInclude(value = Include.NON_NULL)
  String errordetails;

  public String getResult()
  {
    return result;
  }

  public void setResult(String result)
  {
    this.result = result;
  }

  public String getErrordetails()
  {
    return errordetails;
  }

  public void setErrordetails(String errordetails)
  {
    this.errordetails = errordetails;
  }
}
