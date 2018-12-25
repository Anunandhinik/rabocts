package com.cogniz.interv.rabobank.manoj.ws.rest;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cogniz.interv.rabobank.manoj.aspects.RaboRestController;
import com.cogniz.interv.rabobank.manoj.exceptions.RaboException;
import com.cogniz.interv.rabobank.manoj.protocol.BaseResponse;

@ControllerAdvice(annotations = RaboRestController.class)
public class RaboExceptionHandler
{
  
  private static Logger logger = LoggerFactory.getLogger(RaboExceptionHandler.class);

  @ResponseBody
  @ExceptionHandler(RaboException.class)
  public ResponseEntity<?> handleRaboExceptions(HttpServletRequest httpRequest,
      RaboException exception)
  {
    String randomUUID = UUID.randomUUID().toString();
    logger.error(String.format("Exception: %s. UUID: %s", exception.getError().getMessage(), randomUUID), exception.getThrowable());
    BaseResponse response = new BaseResponse();
    response.setResult("An error occured while processing. Exception id: " + randomUUID);
    response.setErrordetails(exception.getError().getMessage());
    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ResponseBody
  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> handleExceptions(HttpServletRequest httpRequest,
      Exception exception)
  {
    String randomUUID = UUID.randomUUID().toString();
    logger.error(String.format("Exception: %s. UUID: %s", exception.getLocalizedMessage(), randomUUID), exception);
    BaseResponse response = new BaseResponse();
    response.setResult("An error occured while processing. Exception id: " + randomUUID);
    response.setErrordetails(exception.getMessage());
    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
