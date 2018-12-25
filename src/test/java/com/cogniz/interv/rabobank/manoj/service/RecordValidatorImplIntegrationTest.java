package com.cogniz.interv.rabobank.manoj.service;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cogniz.interv.rabobank.manoj.exceptions.RaboException;
import com.cogniz.interv.rabobank.manoj.model.StatementErrorRecord;
import com.cogniz.interv.rabobank.manoj.model.utils.ErrorReason;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RecordValidatorImplIntegrationTest
{

  @Autowired
  RecordValidatorService recordValidatorService;

  @Test
  public void testValidateCsvFile() throws Exception
  {
    File file = Paths.get(getClass().getClassLoader().getResource("csvIntegration.csv").toURI()).toFile();
    
    List<StatementErrorRecord> errors = recordValidatorService.validateCsvRecordFile(file);
    Assert.assertTrue(errors.size()==2);
    Assert.assertEquals("NL27SNSB0917829871", errors.get(0).getStatementRecord().getAccountNumber());
    Assert.assertEquals(ErrorReason.MUTATION_ERROR, errors.get(0).getErrorReason());
    Assert.assertEquals("NL27SNSB0917829873", errors.get(1).getStatementRecord().getAccountNumber());
    Assert.assertEquals(ErrorReason.DUPLICATE_REF, errors.get(1).getErrorReason());
  }
  
  @Test
  public void testValidateXmlFile() throws Exception
  {
    File file = Paths.get(getClass().getClassLoader().getResource("xmlIntegration.xml").toURI()).toFile();
    
    List<StatementErrorRecord> errors = recordValidatorService.validateXmlRecordFile(file);
    Assert.assertTrue(errors.size()==2);
    Assert.assertEquals("NL93ABNA0585619023", errors.get(0).getStatementRecord().getAccountNumber());
    Assert.assertEquals(ErrorReason.MUTATION_ERROR, errors.get(0).getErrorReason());
    Assert.assertEquals("NL93ABNA0585619025", errors.get(1).getStatementRecord().getAccountNumber());
    Assert.assertEquals(ErrorReason.DUPLICATE_REF, errors.get(1).getErrorReason());
  }
  
  @Test(expected = RaboException.class)
  public void testException() throws Exception
  {
      File file = Paths.get(getClass().getClassLoader().getResource("xmlIntegration.xml").toURI()).toFile();
      recordValidatorService.validateCsvRecordFile(file);
  }
}
