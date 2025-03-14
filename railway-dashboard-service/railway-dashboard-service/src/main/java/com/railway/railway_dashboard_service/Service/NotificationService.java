package com.railway.railway_dashboard_service.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.railway.railway_dashboard_service.dto.NotificationDTO;
import com.railway.railway_dashboard_service.entity.Notifications;
import com.railway.railway_dashboard_service.repository.NotificationRepository;

@Service
public class NotificationService 
{
	   private final Logger logger = LogManager.getLogger(NotificationService.class);
	   // inject the repository for database intraction		   
       private final NotificationRepository notificationRepository;
       
       @Autowired
       // Constructor Injection for loosely coupling
       public NotificationService(NotificationRepository notificationRepository)
       {
    	   this.notificationRepository = notificationRepository;
       }
       
       
       
       //create or update Notification
       public NotificationDTO saveNotification(NotificationDTO notificationDTO)
       {    	     
    	   // Create a new Notification entity from DTO
           Notifications notification = new Notifications();
           notification.setId(notificationDTO.getId());
           notification.setMessage(notificationDTO.getMessage());
           notification.setReadStatus(notificationDTO.getReadStatus());
           notification.setTimestamp(notificationDTO.getTimeStamp());
           notification.setAlerTime(notificationDTO.getAlerTime());
           notification.setUserCondect(notificationDTO.getUserConduct());
           
           notificationRepository.save(notification);
           logger.info("Notification save {}",notification);
           return new NotificationDTO
        		  (notificationDTO.getId(),
        		   notificationDTO.getMessage(), 
        		   notificationDTO.getReadStatus(), 
        		   notificationDTO.getTimeStamp(), 
        		   notificationDTO.getAlerTime(), 
        		   notificationDTO.getUserConduct()
        		   );
       }
       
       //get all Notification 
       public List<NotificationDTO> getAllNotification()
       {
    	    List<Notifications> notifications = notificationRepository.findAll();
    	    // Log fetching of notifications
            logger.info("Fetched all notifications from the database");   	    
    	    // Convert entity list to DTO list and return
    	    return notifications
    	    	   .stream()
    	    	   .map(notification ->  new NotificationDTO(
    	    	    notification.getId(),
    	    	    notification.getMessage(),
    	    	    notification.getReadStatus(),
    	    	    notification.getTimestamp(),
    	    	    notification.getAlerTime(),
    	    	    notification.getUserCondect()
    	    	    ))
    	    	   .collect(Collectors.toList());
       }
       
       //get notification by id
       public NotificationDTO getNotificationById(Long id)
       {
    	   Optional<Notifications> notifications = notificationRepository.findById(id);
           if(notifications.isPresent())
           {
        	   Notifications notifi = notifications.get();
        	   return new NotificationDTO(
        	   notifi.getId(),
        	   notifi.getMessage(),
        	   notifi.getReadStatus(),
        	   notifi.getTimestamp(),
        	   notifi.getAlerTime(),
        	   notifi.getUserCondect());
           }
           else
           {
        	   logger.error("Notification with ID {} not found", id);
        	   throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Notification not Found");
           }
       }
       // delete the notification by id
       public void deleteNotification(Long id)
       {  
    	   logger.info("Attempting to delete notification with ID: {}", id);
    	   notificationRepository.deleteById(id);
    	   logger.info("Notification with ID {} deleted successfully", id);
       }            
       
}
