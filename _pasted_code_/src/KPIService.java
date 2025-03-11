

import com.railway.railway_dashboard_service.dto.KPIDTO;
import com.railway.railway_dashboard_service.entity.KPI;
import com.railway.railway_dashboard_service.repository.KPIRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import jakarta.transaction.Transactional;

import java.util.Optional;

@Service
public class KPIService {

    // Logger tracks activities in the KPI Service Layer
    private final Logger logger = LogManager.getLogger(KPIService.class);

    // Inject the repository for database interaction
    private final KPIRepository kpiRepository;

    // Constructor-based injection for loose coupling
    @Autowired
    public KPIService(KPIRepository kpiRepository) {
        this.kpiRepository = kpiRepository;
    }


    @Cacheable(value = "kpiCache", key = "#root.methodName")
    public KPI getCurrentKPI() {
        logger.info("Fetching the most recent KPI data...");

        // Retrieve the most recent KPI data
        KPI kpi = kpiRepository.findTopByOrderByIdDesc();

        // If no KPI data is found, throw a custom exception
        if (kpi == null) {
            logger.error("No KPI data found in the database.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No KPI Data Found");
        }

        logger.info("Successfully retrieved the KPI data: {}", kpi);
        return kpi;
    }

    /**
     * Saves a new KPI entity to the database.
     * Maps the provided DTO to the KPI entity, saves it, and updates the cache.
     * 
     * @param kpiDTO Data Transfer Object containing KPI values.
     * @return the saved KPI entity.
     */
    @Transactional
    public KPI saveKPI(KPIDTO kpiDTO) {
        logger.info("Saving new KPI data: {}", kpiDTO);

        // Convert the DTO to an entity
        KPI kpi = new KPI();
        kpi.setTotalBooking(kpiDTO.getTotalBooking());
        kpi.setTotalRevenue(kpiDTO.getTotalRevenue());
        kpi.setConversionRate(kpiDTO.getConversionRate());
        kpi.setTotalCancellations(kpiDTO.getTotalCancellations());
        kpi.setAverageBookingTime(kpiDTO.getAverageBookingTime());
        kpi.setTrainOccupancyRate(kpiDTO.getTrainOccupancyRate());

        // Save the KPI to the database
        KPI savedKPI = kpiRepository.save(kpi);
        logger.info("KPI saved successfully with ID: {}", savedKPI.getId());

        // Update the cache with the new KPI
        updateCache(savedKPI);

        return savedKPI;
    }

    /**
     * Updates the cache with the provided KPI.
     * 
     * @param kpi The KPI entity to store in the cache.
     * @return the updated KPI entity.
     */
    @CachePut(value = "kpiCache", key = "#kpi.id")
    private KPI updateCache(KPI kpi) {
        return kpi;
    }

    /**
     * Updates an existing KPI entity by its ID.
     * If the KPI does not exist, a 404 Not Found exception is thrown.
     * 
     * @param id The ID of the KPI to update.
     * @param updateKPI The updated KPI entity.
     * @return the updated KPI entity.
     * @throws ResponseStatusException if the KPI with the given ID does not exist.
     */
    @Transactional
    public KPI updateKPI(Long id, KPI updateKPI) {
        logger.info("Updating KPI data for ID: {}", id);

        // Retrieve the existing KPI by ID
        Optional<KPI> existingKPIOptional = kpiRepository.findById(id);

        // If the KPI with the provided ID does not exist, throw a 404 error
        if (existingKPIOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "KPI with id " + id + " not found");
        }

        // Get the existing KPI entity
        KPI existingKPI = existingKPIOptional.get();

        // Update the existing entity with the new values from the updateKPI object
        existingKPI.setTotalBooking(updateKPI.getTotalBooking());
        existingKPI.setTotalRevenue(updateKPI.getTotalRevenue());
        existingKPI.setTotalCancellations(updateKPI.getTotalCancellations());
        existingKPI.setConversionRate(updateKPI.getConversionRate());
        existingKPI.setAverageBookingTime(updateKPI.getAverageBookingTime());
        existingKPI.setTrainOccupancyRate(updateKPI.getTrainOccupancyRate());

        // Save the updated KPI entity
        KPI updatedKPI = kpiRepository.save(existingKPI);
        logger.info("KPI updated successfully with ID: {}", id);

        // Update the cache with the updated KPI
        updateCache(updatedKPI);

        return updatedKPI;
    }

    /**
     * Deletes the KPI entity with the given ID.
     * Throws a 404 error if the KPI does not exist.
     * 
     * @param id The ID of the KPI to delete.
     * @throws ResponseStatusException if the KPI with the given ID does not exist.
     */
    @Transactional
    public void deleteKPI(Long id) {
        logger.info("Deleting KPI with ID: {}", id);

        // Check if the KPI exists
        if (!kpiRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "KPI with id " + id + " not found");
        }

        // Delete the KPI entity
        kpiRepository.deleteById(id);
        logger.info("KPI with ID: {} deleted successfully.", id);

        // Clear the cache after deletion
        updateCache(null);
    }
}
