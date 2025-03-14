package com.railway.railway_dashboard_service.Service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.railway.railway_dashboard_service.dto.KPIDTO;
import com.railway.railway_dashboard_service.entity.KPI;
import com.railway.railway_dashboard_service.repository.KPIRepository;

import jakarta.transaction.Transactional;

@Service
public class KPIService  
{   
	
	 // Logger track the activities the KPI Service Layer
	 private final Logger logger = LogManager.getLogger(KPIService.class);
	 
	 // Inject the repository for database Interaction 
     private final KPIRepository kpiRepository;
     
     // constructor based injection for loosely coupling
     @Autowired
     public KPIService(KPIRepository kpiRepository)
     {
    	     this.kpiRepository = kpiRepository;
     }
     
     // Get Current KPI Data
     @Cacheable(value = "kpichache", key = "#root.methodName")
     public KPI getCurrentKPI()
     {
	    	 logger.info("Fetching the Most Recent KPI Datas.....");
	    	 KPI kpi = kpiRepository.findTopByOrderByIdDesc();
	    	 
	    	// If no KPI Data is found throw the custom Exception
	    	 if(kpi == null)
	    	 {
	    		 logger.error("No KPI data Found In the database..");
	    		 throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No KPI Data Found");
	    	 }
	    	 logger.info("Successfully retrive the kip data {}", kpi);
	    	 return kpi;
     }
     // Save a new KPI using the provided DTO (Data Transfer Object)
  // @Transactional ensures saving is correct. If it fails, it rolls back.
     @Transactional
     public KPI saveKPI(KPIDTO kpiDTO)
     {
    	     logger.info("Saving new KPI data{}", kpiDTO);
    	     
    	     //convert DTO into entity
    	     KPI kpi = new KPI();
    	     kpi.setTotalBooking(kpiDTO.getTotalBooking());
    	     kpi.setAverageBookingTime(kpiDTO.getAverageBookingtime());
    	     kpi.setConversionRate(kpi.getConversionRate());
    	     kpi.setTotalCancellations(kpiDTO.getTotalCencellation());
    	     kpi.setTotalRevenue(kpiDTO.getTotalRevenue());;
    	     kpi.setTrainOccupacencyrate(kpiDTO.getTrainOccupancyRate());
    	     
    	     // save KPI and return the Entity
    	     KPI saveKPI = kpiRepository.save(kpi);
    	     logger.info("save KPI successfull with ID {}", saveKPI.getId());
    	     
    	     updateCache(saveKPI);
    	     
    	     return saveKPI;
     }
     
     @CachePut(value = "kpicache", key = "#kpi.id") 
	 private KPI updateCache(KPI kpi) 
	 {

		     return kpi;
	 }
     
     // Update an existing KPI by its ID using the provided DTO
     // @Transactional ensures updating is correct. If it fails, it rolls back.
     @Transactional
     public KPI updateKPI(Long id, KPIDTO kpiDTO)     
     {
    	    logger.info("update KPI data for id:{}", id);
    	    // Fetch the existing KPI from the database
    	    Optional<KPI> existingKPIOptional = kpiRepository.findById(id);
    	    // If the KPI doesn't exist, throw an exception
    	    if(existingKPIOptional.isEmpty())
    	    {
    	    	throw new ResponseStatusException(HttpStatus.NOT_FOUND, "KPI with id" + id + "not found");
    	    }
    	    
    	    // get the existing KPI record
    	    KPI existingKPI = existingKPIOptional.get();
    	    {
    	    	//update the field of the existing KPI new with the value
    	    	existingKPI.setTotalBooking(kpiDTO.getTotalBooking());
    	    	existingKPI.setTotalRevenue(kpiDTO.getTotalRevenue());
    	    	existingKPI.setTotalCancellations(kpiDTO.getTotalCencellation());
    	    	existingKPI.setConversionRate(kpiDTO.getConversionRate());
    	    	existingKPI.setAverageBookingTime(kpiDTO.getAverageBookingtime());
    	    	existingKPI.setTrainOccupacencyrate(kpiDTO.getTrainOccupancyRate());;
    	    	
    	    	// save the update KPI
    	    	KPI updateEnity = kpiRepository.save(existingKPI);
    	    	logger.info("KPI update in Successflly With ID:{}", id);
    	    	
    	    	// update cache the new KPI
    	    	updateCache(updateEnity);
    	    	return updateEnity;   	    	
    	    }  	    
       } 
       
       //Delete the KPI record By Id
      // @Transactional ensures deleting is correct. If it fails, it rolls back.
       @Transactional
       public void deleteKPI(Long id)
       {
    	        logger.info("Deleting KPI with id {}", id);               
    	        // Check If the KPI exists
    	        if(!kpiRepository.existsById(id))
    	        {
    	        	throw new ResponseStatusException(HttpStatus.NOT_FOUND, "KPI with id" + id + "not found");
    	        }
    	        //Delete the KPI
    	         kpiRepository.deleteById(id);
    	         logger.info("KPI with id Deleted succesfuilly", id);
    	         // Remove the KPI data from the cache (set to null)
    	         updateCache(null);   	        
       }
       
       public List<KPI> getAllKPIs() {
           List<KPI> kpis = kpiRepository.findAll();
           // Check if the list is empty and throw a professional exception with a message
           if (kpis.isEmpty()) {
               throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No KPIs found.");
           }
           
           return kpis;
       }
	       
}    
    
    

