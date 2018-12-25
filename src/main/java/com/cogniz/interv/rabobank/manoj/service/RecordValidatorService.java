package com.cogniz.interv.rabobank.manoj.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.cogniz.interv.rabobank.manoj.exceptions.RaboException;
import com.cogniz.interv.rabobank.manoj.model.StatementErrorRecord;
import com.cogniz.interv.rabobank.manoj.model.StatementRecord;
import com.cogniz.interv.rabobank.manoj.validators.StatementRecordValidator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface RecordValidatorService
{

  /**
   * Parse a given XML file and get a list of {@link StatementRecord}
   * and then check the validity of the statements.
   * Uses {@link StatementRecordValidator#validateStatements(List)} to check the validity.
   * @param file - input XML file
   * @return a list of {@link StatementErrorRecord} which contains the erroneous transactions. 
   * @throws RaboException
   */
  List<StatementErrorRecord> validateXmlRecordFile(File file) throws RaboException;
  
  /**
   *  Parse a given CSV file and get a list of {@link StatementRecord}
   * and then check the validity of the statements.
   * Uses {@link StatementRecordValidator#validateStatements(List)} to check the validity.
   * @param file- input XML file
   * @return a list of {@link StatementErrorRecord} which contains the erroneous transactions. 
   * @throws RaboException
   */
  List<StatementErrorRecord> validateCsvRecordFile(File file) throws RaboException;
}
