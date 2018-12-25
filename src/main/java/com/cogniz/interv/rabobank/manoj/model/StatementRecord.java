package com.cogniz.interv.rabobank.manoj.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class StatementRecord
{

  @JsonProperty("Reference")
  @JacksonXmlProperty(localName="reference")
  long reference;
  
  @JsonProperty("AccountNumber")
  @JacksonXmlProperty(localName="accountNumber")
  String accountNumber;
  
  @JsonProperty("Description")
  @JacksonXmlProperty(localName="description")
  String description;
  
  @JsonProperty("Start Balance")
  @JacksonXmlProperty(localName="startBalance")
  BigDecimal startBalance;
  
  @JsonProperty("Mutation")
  @JacksonXmlProperty(localName="mutation")
  BigDecimal mutation;
  
  @JsonProperty("End Balance")
  @JacksonXmlProperty(localName="endBalance")
  BigDecimal endBalance;

  // private for ORM use.
  private StatementRecord()
  {

  }

  public long getReference()
  {
    return reference;
  }

  public void setReference(long reference)
  {
    this.reference = reference;
  }

  public String getAccountNumber()
  {
    return accountNumber;
  }

  public void setAccountNumber(String accountNumber)
  {
    this.accountNumber = accountNumber;
  }

  public String getDescription()
  {
    return description;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

  public BigDecimal getStartBalance()
  {
    return startBalance;
  }

  public void setStartBalance(BigDecimal startBalance)
  {
    this.startBalance = startBalance;
  }

  public BigDecimal getMutation()
  {
    return mutation;
  }

  public void setMutation(BigDecimal mutation)
  {
    this.mutation = mutation;
  }

  public BigDecimal getEndBalance()
  {
    return endBalance;
  }

  public void setEndBalance(BigDecimal endBalance)
  {
    this.endBalance = endBalance;
  }

  
  @Override
  public boolean equals(Object obj)
  {
    if(!(obj instanceof StatementRecord)) 
      return false;
    return ((StatementRecord)obj).reference == reference;
  }
  
  @Override
  public int hashCode() 
  {
    return String.valueOf(reference).hashCode();
  }

  // Pretty print for debug
  @Override
  public String toString()
  {
    return String.format("Reference: %s from Account: %s. Start: %s, Change: %s, End: %s", this.reference,
        this.accountNumber, this.startBalance, this.mutation, this.endBalance);
  }
}
