package com.cogniz.interv.rabobank.manoj.exceptions;

public enum RaboError
{

  INVALID_FILE_INPUT("Error reading input file"),
  CSV_PARSE_ERROR("Error parsing CSV file"),
  XML_PARSE_ERROR("Error parsing XML file");
  
  String message;
  private RaboError(String message)
  {
    this.message = message;
  }
  public String getMessage()
  {
    return message;
  }
}
