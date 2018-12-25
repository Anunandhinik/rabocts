package com.cogniz.interv.rabobank.manoj.protocol;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StatementErrorRecordResponse
{

  @JsonProperty("reference")
  long referenceNumber;
  
  @JsonProperty("account")
  String accountNumber;
  
  @JsonProperty("reason")
  String reason;

  public long getReferenceNumber()
  {
    return referenceNumber;
  }

  public void setReferenceNumber(long referenceNumber)
  {
    this.referenceNumber = referenceNumber;
  }

  public String getAccountNumber()
  {
    return accountNumber;
  }

  public void setAccountNumber(String accountNumber)
  {
    this.accountNumber = accountNumber;
  }

  public String getReason()
  {
    return reason;
  }

  public void setReason(String reason)
  {
    this.reason = reason;
  }
}
