package com.cogniz.interv.rabobank.manoj.fileReaders;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cogniz.interv.rabobank.manoj.exceptions.RaboError;
import com.cogniz.interv.rabobank.manoj.exceptions.RaboException;
import com.cogniz.interv.rabobank.manoj.model.StatementRecord;
import com.cogniz.interv.rabobank.manoj.model.utils.Constants;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@Component
public class FileObjectReader
{

  public <T> List<T> readFromCsvFile(Class<T> type, File file) throws RaboException
  {
    try
    {
      CsvSchema initProps = CsvSchema.emptySchema().withHeader().withColumnSeparator(Constants.CSV_SEPERATOR);
      CsvMapper mapper = new CsvMapper();
      MappingIterator<T> modelObjectList = mapper.readerFor(type).with(initProps).readValues(file);
      return modelObjectList.readAll();
    }
    catch (IOException ioException)
    {
      throw new RaboException(RaboError.CSV_PARSE_ERROR, ioException);
    }
  }

  public <T> List<T> readFromXmlFile(Class<T> type, File file)
      throws RaboException
  {
    try
    {
      XmlMapper xmlMapper = new XmlMapper();

      List<T> modelObjectList = null;
      // TO-DO Figure out a way to TypeReference dynamically without if-else's
      if (type == StatementRecord.class)
        modelObjectList = xmlMapper.readValue(new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8),
            new TypeReference<List<StatementRecord>>()
            {
            });

      // this will most certainly not work in most cases as the "type" cannot be wrapped inside the xml header.
      else
        modelObjectList = (List<T>) xmlMapper.readValue(
            new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8),
            type);
      return modelObjectList;
    }
    catch (IOException ioException)
    {
      throw new RaboException(RaboError.XML_PARSE_ERROR, ioException);
    }

  }

}
