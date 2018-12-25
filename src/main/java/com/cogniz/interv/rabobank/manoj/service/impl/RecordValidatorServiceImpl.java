package com.cogniz.interv.rabobank.manoj.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cogniz.interv.rabobank.manoj.exceptions.RaboException;
import com.cogniz.interv.rabobank.manoj.fileReaders.FileObjectReader;
import com.cogniz.interv.rabobank.manoj.model.StatementErrorRecord;
import com.cogniz.interv.rabobank.manoj.model.StatementRecord;
import com.cogniz.interv.rabobank.manoj.model.utils.Constants;
import com.cogniz.interv.rabobank.manoj.service.RecordValidatorService;
import com.cogniz.interv.rabobank.manoj.validators.StatementRecordValidator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Service
public class RecordValidatorServiceImpl implements RecordValidatorService
{

  @Autowired
  @Qualifier(Constants.VALID_FIRST_VALIDATOR)
  StatementRecordValidator statementRecordValidator;
  
  @Autowired
  FileObjectReader fileObjectReader;
  
  @Override
  public List<StatementErrorRecord> validateXmlRecordFile(File file) throws RaboException
  {
    List<StatementRecord> records = fileObjectReader.readFromXmlFile(StatementRecord.class, file);
    return statementRecordValidator.validateStatements(records);
  }

  @Override
  public List<StatementErrorRecord> validateCsvRecordFile(File file) throws RaboException
  {
    List<StatementRecord> records = fileObjectReader.readFromCsvFile(StatementRecord.class, file);
    return statementRecordValidator.validateStatements(records);
  }

}
