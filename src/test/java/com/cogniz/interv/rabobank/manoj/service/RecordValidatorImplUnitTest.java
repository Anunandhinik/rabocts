package com.cogniz.interv.rabobank.manoj.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.cogniz.interv.rabobank.manoj.fileReaders.FileObjectReader;
import com.cogniz.interv.rabobank.manoj.model.StatementRecord;
import com.cogniz.interv.rabobank.manoj.service.impl.RecordValidatorServiceImpl;
import com.cogniz.interv.rabobank.manoj.validators.StatementRecordValidator;

public class RecordValidatorImplUnitTest
{
  
  @InjectMocks
  RecordValidatorServiceImpl recordValidatorService;
  
  @Mock
  StatementRecordValidator statementRecordValidator;
  
  @Mock
  FileObjectReader fileObjectReader;

  @Before
  public void beforeClass()
  {
    MockitoAnnotations.initMocks(this);
  }
  
  @Test
  public void testValidateXmlRecordFile()
  {
    Mockito.when(fileObjectReader.readFromXmlFile(Mockito.any(), Mockito.any())).thenReturn(new ArrayList<>());
    Mockito.when(statementRecordValidator.validateStatements(Mockito.anyList())).thenReturn(new ArrayList<>());
    
    recordValidatorService.validateXmlRecordFile(new File(""));
    
    Mockito.verify(fileObjectReader, Mockito.times(1)).readFromXmlFile(Mockito.any(), Mockito.any());
    Mockito.verify(statementRecordValidator, Mockito.times(1)).validateStatements(Mockito.anyList());
  }
  
  @Test
  public void testValidateCsvRecordFile()
  {
    Mockito.when(fileObjectReader.readFromCsvFile(Mockito.any(), Mockito.any())).thenReturn(new ArrayList<>());
    Mockito.when(statementRecordValidator.validateStatements(Mockito.anyList())).thenReturn(new ArrayList<>());
    
    recordValidatorService.validateCsvRecordFile(new File(""));
    
    Mockito.verify(fileObjectReader, Mockito.times(1)).readFromCsvFile(Mockito.any(), Mockito.any());
    Mockito.verify(statementRecordValidator, Mockito.times(1)).validateStatements(Mockito.anyList());
  }
}
