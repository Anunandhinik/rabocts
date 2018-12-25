package com.cogniz.interv.rabobank.manoj.ws.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cogniz.interv.rabobank.manoj.aspects.RaboRestController;
import com.cogniz.interv.rabobank.manoj.converter.StatementResponseConverter;
import com.cogniz.interv.rabobank.manoj.model.StatementErrorRecord;
import com.cogniz.interv.rabobank.manoj.model.utils.ResourceMapping;
import com.cogniz.interv.rabobank.manoj.service.RecordValidatorService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RaboRestController
public class StatementRecordController
{

  @Autowired
  StatementResponseConverter statementResponseConverter;

  @Autowired()
  RecordValidatorService recordValidatorService;

  @RequestMapping(value = ResourceMapping.VERIFY_STATEMENT, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<?>
      verifyStatement(@RequestParam(value = ResourceMapping.FILE_PARAM, required = true) MultipartFile recordStatements)
          throws JsonParseException, JsonMappingException, IOException
  {
    File file = convertMultiPartFile(recordStatements);
    List<StatementErrorRecord> error = null;
    if (recordStatements.getContentType().equalsIgnoreCase("text/csv"))
    {
      error = recordValidatorService.validateCsvRecordFile(file);
    }
    if (recordStatements.getContentType().equalsIgnoreCase("application/xml"))
    {
      error = recordValidatorService.validateXmlRecordFile(file);
    }
    
    return new ResponseEntity<>(statementResponseConverter.convert(error), HttpStatus.OK);
  }

  private File convertMultiPartFile(MultipartFile recordStatements) throws IllegalStateException, IOException
  {
    File convFile = new File(recordStatements.getOriginalFilename());
    convFile.createNewFile(); 
    FileOutputStream fos = new FileOutputStream(convFile); 
    fos.write(recordStatements.getBytes());
    fos.close(); 
    return convFile;
  }
}
