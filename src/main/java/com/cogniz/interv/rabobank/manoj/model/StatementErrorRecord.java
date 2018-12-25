package com.cogniz.interv.rabobank.manoj.model;

import com.cogniz.interv.rabobank.manoj.model.utils.ErrorReason;

public class StatementErrorRecord
{

  StatementRecord statementRecord;
  ErrorReason errorReason;
  
  public StatementErrorRecord(StatementRecord statementRecord, ErrorReason errorReason)
  {
    super();
    this.statementRecord = statementRecord;
    this.errorReason = errorReason;
  }

  public StatementRecord getStatementRecord()
  {
    return statementRecord;
  }
  public ErrorReason getErrorReason()
  {
    return errorReason;
  }
  
  @Override
  public String toString()
  {
    return String.format("Error transaction reference: %s. Reason: %s", statementRecord.reference, errorReason.getReasonDescription());
  }
  
}
