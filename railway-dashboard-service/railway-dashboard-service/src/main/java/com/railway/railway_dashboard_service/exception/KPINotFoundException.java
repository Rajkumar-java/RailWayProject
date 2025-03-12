package com.railway.railway_dashboard_service.exception;

public class KPINotFoundException extends RuntimeException
{ 
      public KPINotFoundException(String message)
      {
    	  super(message);
      }
}
