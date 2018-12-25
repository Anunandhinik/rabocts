package com.cogniz.interv.rabobank.manoj.exceptions;

public class RaboException extends RuntimeException
{

  private RaboError error;
  private Throwable throwable;
  
  private static final long serialVersionUID = 2285403744345774035L;
  
  public RaboException(RaboError error)
  {
    super(error.message);
    this.error = error;
  }
  
  public RaboException(RaboError error, Throwable throwable)
  {
    super(error.message, throwable);
    this.error = error;
    this.throwable = throwable;
  }

  public RaboError getError()
  {
    return error;
  }

  public Throwable getThrowable()
  {
    return throwable;
  }
  

}
