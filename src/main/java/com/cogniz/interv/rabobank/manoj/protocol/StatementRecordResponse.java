package com.cogniz.interv.rabobank.manoj.protocol;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

public class StatementRecordResponse extends BaseResponse
{
  
  @JsonProperty("errorRecords")
  @JsonInclude(value = Include.NON_NULL)
  List<StatementErrorRecordResponse> errorRecords;

  public String getResult()
  {
    return result;
  }
  public void setResult(String result)
  {
    this.result = result;
  }
  public List<StatementErrorRecordResponse> getErrorRecords()
  {
    return errorRecords;
  }
  public void setErrorRecords(List<StatementErrorRecordResponse> errorRecords)
  {
    this.errorRecords = errorRecords;
  } 
}
