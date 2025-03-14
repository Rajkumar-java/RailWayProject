package com.railway.railway_dashboard_service.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;

public class NotificationDTO 
{
       private Long id;
       
       @NotBlank(message = "message cannod be empty")
       private String message;
       private Boolean readStatus = false;
       private LocalDateTime timeStamp = LocalDateTime.now();
       private String alerTime;
       private String userConduct;
       
       public NotificationDTO()
       {
    	   
       }
       
		public NotificationDTO(Long id, @NotBlank(message = "message cannod be empty") String message, Boolean readStatus,
			LocalDateTime timeStamp, String alerTime, String userConduct) 
		{
		super();
		this.id = id;
		this.message = message;
		this.readStatus = readStatus;
		this.timeStamp = timeStamp;
		this.alerTime = alerTime;
		this.userConduct = userConduct;
	}

		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public Boolean getReadStatus() {
			return readStatus;
		}
		public void setReadStatus(Boolean readStatus) {
			this.readStatus = readStatus;
		}
		public LocalDateTime getTimeStamp() {
			return timeStamp;
		}
		public void setTimeStamp(LocalDateTime timeStamp) {
			this.timeStamp = timeStamp;
		}
		public String getAlerTime() {
			return alerTime;
		}
		public void setAlerTime(String alerTime) {
			this.alerTime = alerTime;
		}
		public String getUserConduct() {
			return userConduct;
		}
		public void setUserConduct(String userConduct) {
			this.userConduct = userConduct;
		}
	              
}
