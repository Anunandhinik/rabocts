package com.cogniz.interv.rabobank.manoj.model.utils;

public enum ErrorReason
{
  
  DUPLICATE_REF("Duplicate reference number for transaction"),
  MUTATION_ERROR("Addition/Deduction mismatch between Start and End balances");
  
  private String reasonDescription;
  
  ErrorReason(String description)
  {
    this.reasonDescription = description;
  }
  
  public String getReasonDescription()
  {
    return this.reasonDescription;
  }

}
