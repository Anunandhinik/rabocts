package com.cogniz.interv.rabobank.manoj.fileReader;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.cogniz.interv.rabobank.manoj.fileReaders.FileObjectReader;
import com.cogniz.interv.rabobank.manoj.model.StatementRecord;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;



public class FileObjectReaderTest {

	@Test
	public void testReadFromCsv() throws IOException, URISyntaxException {
	  
	  FileObjectReader fob = new FileObjectReader();
	  File file = Paths.get(getClass().getClassLoader().getResource("csv.csv").toURI()).toFile();
	  
	  List<StatementRecord> userStament = fob.readFromCsvFile(StatementRecord.class, file);
	  Assert.assertTrue(userStament.size() == 2);
	  Assert.assertEquals(194261, userStament.get(0).getReference());
	  Assert.assertEquals("NL91RABO0315273637", userStament.get(0).getAccountNumber());
	  Assert.assertEquals(new BigDecimal("-41.83"), userStament.get(0).getMutation());
	  Assert.assertEquals(112806, userStament.get(1).getReference());
	  Assert.assertEquals("NL27SNSB0917829871", userStament.get(1).getAccountNumber());
	  Assert.assertEquals(new BigDecimal("15.57"), userStament.get(1).getMutation());

	  
	}
	
	@Test
  public void testReadFromXml() throws JsonParseException, JsonMappingException, IOException, URISyntaxException
  {
	  FileObjectReader fob = new FileObjectReader();
    File file = Paths.get(getClass().getClassLoader().getResource("xml.xml").toURI()).toFile();
    
    List<StatementRecord> userStament = fob.readFromXmlFile(StatementRecord.class, file);
    Assert.assertTrue(userStament.size() == 2);
    Assert.assertEquals(130498, userStament.get(0).getReference());
    Assert.assertEquals("NL69ABNA0433647324", userStament.get(0).getAccountNumber());
    Assert.assertEquals(new BigDecimal("-18.78"), userStament.get(0).getMutation());
    Assert.assertEquals(167875, userStament.get(1).getReference());
    Assert.assertEquals("NL93ABNA0585619023", userStament.get(1).getAccountNumber());
    Assert.assertEquals(new BigDecimal("-939"), userStament.get(1).getMutation());
  }

}

