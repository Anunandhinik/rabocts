package com.cogniz.interv.rabobank.manoj.validators;

import java.util.List;

import com.cogniz.interv.rabobank.manoj.model.StatementErrorRecord;
import com.cogniz.interv.rabobank.manoj.model.StatementRecord;

public interface StatementRecordValidator
{
  /**
   * Check a list of {@link StatementRecord} for validity of references and mutations
   * @param statementOfRecords
   * @return
   */
  List<StatementErrorRecord> validateStatements(List<StatementRecord> statementOfRecords);
  
}
