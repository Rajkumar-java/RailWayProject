package com.railway.railway_dashboard_service.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.railway.railway_dashboard_service.Service.NotificationService;
import com.railway.railway_dashboard_service.dto.NotificationDTO;

@RestController
@RequestMapping("/api/notification")
public class NotificationController 
{
       private final Logger logger = LogManager.getLogger(NotificationController.class);
       
       private final NotificationService notificationService;
       
       @Autowired
       //constructo injection for loosely coupling
       public NotificationController(NotificationService notificationService)
       {
    	   this.notificationService = notificationService;
       }
       
       // save and create all notification
       @PostMapping("/save")
       public ResponseEntity<NotificationDTO>saveNotification(@RequestBody NotificationDTO noticationDTO)
       {
    	   NotificationDTO saveNotification = notificationService.saveNotification(noticationDTO);
           return new ResponseEntity<>(saveNotification,HttpStatus.OK);
       }
       
       //get all Notificaton
       @GetMapping("/allnotification")
       public ResponseEntity<List<NotificationDTO>>getAllNotification()
       {
    	    List<NotificationDTO> getAllNotification = notificationService.getAllNotification();
            return new ResponseEntity<>(getAllNotification,HttpStatus.OK);
       }
       
       //get Notification by id
       @GetMapping("getNotification/{id}")
       public ResponseEntity<NotificationDTO>getNotificationById(@PathVariable Long id)
       {
    	   NotificationDTO notification = notificationService.getNotificationById(id); 
    	   logger.info("Retrieved notification with ID: {}", id);
    	   return new ResponseEntity<>(notification,HttpStatus.OK);
       }
       
    // Delete Notification
       @DeleteMapping("/{id}")
       public ResponseEntity<Void> deleteNotification(@PathVariable Long id) 
       {
           notificationService.deleteNotification(id);
           logger.info("Successfully deleted notification with ID: {}", id);
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       }
}
