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
import org.springframework.web.server.ResponseStatusException;

import com.railway.railway_dashboard_service.Service.KPIService;
import com.railway.railway_dashboard_service.dto.KPIDTO;
import com.railway.railway_dashboard_service.entity.KPI;

@RestController
@RequestMapping("/api/kpi")
public class KPIController 
{
	  // Logger track the activities the KPI Service Layer
      private final Logger logger = LogManager.getLogger(KPIController.class);
     
      // inject the KPIService for handle the business Logic
      private final KPIService kpiService;
      
      // COnstructor injection for loosely couple
      @Autowired
      public KPIController (KPIService kpiService)
      {
    	  this.kpiService = kpiService;
      }
      
      // Fetch the most recent KPI (GET Request)
      @GetMapping("/current")
      public ResponseEntity<KPI> getCurrentKPI()
      {
    	  logger.info("Fetching the most recent KPI..");
    	  KPI kpi = kpiService.getCurrentKPI(); 
    	  if(kpi !=null)
    	  {   // Return the KPI with HTTP 200 OK
    		 return new ResponseEntity<>(kpi,HttpStatus.OK); 
    	  }
    	  else
    	  {
    		  logger.error("Not found KPI data");
    		  throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NO KPI data found");
    	  }
      }
      
      //Save the KPI
      @PostMapping("/save")
      public ResponseEntity<KPI> saveKPI(@RequestBody KPIDTO kpiDTO)
      {
    	   logger.info("Save new KPI");
    	   KPI savedKPI = kpiService.saveKPI(kpiDTO); 
    	   logger.info("Save succesfull KPI with id{}", savedKPI.getId());
    	   // Return the saved KPI with HTTP 201 CREATED
    	   return new ResponseEntity<KPI>(savedKPI, HttpStatus.CREATED);
      }
      
      //update KPI
      @PostMapping("/update/{id}")
      public ResponseEntity<KPI>updateKPI(@PathVariable Long id,@RequestBody KPIDTO kpiDTO)
      {
    	   logger.info("update KPI with ID {}", id);
    	   KPI updateKPI = kpiService.updateKPI(id, kpiDTO);
    	   logger.info("Succesfull updath KPI with id{}",id);
    	   // Return the updated KPI with HTTP 200 OK
    	   return new ResponseEntity<>(updateKPI, HttpStatus.OK);
      }
      
      // delete kpi with id
      @DeleteMapping("/delete/{id}")
      public ResponseEntity<KPI>deleteKPI(@PathVariable Long id)
      {   
          logger.info("delete KPI with ID: {}", id);
    	  kpiService.deleteKPI(id);
          logger.info("Successfully deleted KPI with ID: {}", id);
          // Return HTTP 204 No Content as the response
    	  return new ResponseEntity<>(HttpStatus.NO_CONTENT);    	  
      }
      //
      @GetMapping("/all")
      public ResponseEntity<List<KPI>> getAllKPIs() {
          logger.info("Fetching all KPI records.");
          List<KPI> kpis = kpiService.getAllKPIs();
         // Return the list of KPIs with HTTP 200 OK
          return new ResponseEntity<>(kpis, HttpStatus.OK);
      }     
      
}
