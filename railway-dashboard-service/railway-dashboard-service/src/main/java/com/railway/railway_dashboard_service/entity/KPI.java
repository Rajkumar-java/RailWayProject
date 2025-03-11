package com.railway.railway_dashboard_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * 
 */
@Entity
@Table(name = "kpi")
public class KPI 
{      
	   @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)   //Automaticaly Generate Primary key
       private Long id;
	   
	   @NotNull(message = "Total Booking cannot be null")
	   @Min(value = 0, message = "Total Booking must be least 0")
	   @Column(nullable = false)
       private Long totalBooking;
	   
	   @NotNull(message = "Total Revenue cannot be null")
	   @Min(value = 0, message = "Total Revenue must be least 0")
	   @Column(nullable = false)
       private Double totalRevenue;
	   
	   @NotNull(message = "Convetsion Rate cannot be null")
	   @Min(value = 0, message = "Conversiona Rate must be least 0")
	   @Column(nullable = false)
       private Double conversionRate;
	   
	   @NotNull(message = "Total Cancellation cannot be null")
	   @Min(value = 0, message = "Total Cancellations must be 0") 
	   @Column(nullable = false)
       private Long totalCancellations;
	   
	   @NotNull(message = "Average  Booking time cannot null")
	   @Min(value = 0, message = "Average Booking time must be 0")
	   @Column(nullable = false)
       private Double averageBookingTime;
	   
	   @NotNull(message = "Train Occupacency rate cannot null")
	   @Min(value = 0, message = "Train Occupacency rate must be 0")
	   @Column(nullable = false)
       private Double trainOccupacencyrate;
	   
//     No Parametor Constructor for Intialization the values , For JPA
	   public KPI()
	   {
		   
	   }
//     Parametor Constructor for Easy To Created The Objects
	   public KPI(Long id,
			@NotNull(message = "Total Booking cannot be null") @Min(value = 0, message = "Total Booking must be least 0") Long totalBooking,
			@NotNull(message = "Total Revenue cannot be null") @Min(value = 0, message = "Total Revenue must be least 0") Double totalRevenue,
			@NotNull(message = "Convetsion Rate cannot be null") @Min(value = 0, message = "Conversiona Rate must be least 0") Double conversionRate,
			@NotNull(message = "Total Cancellation cannot be null") @Min(value = 0, message = "Total Cancellations must be 0") Long totalCancellations,
			@NotNull(message = "Average  Booking time cannot null") @Min(value = 0, message = "Average Booking time must be 0") Double averageBookingTime,
			@NotNull(message = "Train Occupacency rate cannot null") @Min(value = 0, message = "Train Occupacency rate must be 0") Double trainOccupacencyrate) 
	   {
			super();
			this.id = id;
			this.totalBooking = totalBooking;
			this.totalRevenue = totalRevenue;
			this.conversionRate = conversionRate;
			this.totalCancellations = totalCancellations;
			this.averageBookingTime = averageBookingTime;
			this.trainOccupacencyrate = trainOccupacencyrate;
	   }
			public Long getId() {
				return id;
			}
			public void setId(Long id) {
				this.id = id;
			}
			public Long getTotalBooking() {
				return totalBooking;
			}
			public void setTotalBooking(Long totalBooking) {
				this.totalBooking = totalBooking;
			}
			public Double getTotalRevenue() {
				return totalRevenue;
			}
			public void setTotalRevenue(Double totalRevenue) {
				this.totalRevenue = totalRevenue;
			}
			public Double getConversionRate() {
				return conversionRate;
			}
			public void setConversionRate(Double conversionRate) {
				this.conversionRate = conversionRate;
			}
			public Long getTotalCancellations() {
				return totalCancellations;
			}
			public void setTotalCancellations(Long totalCancellations) {
				this.totalCancellations = totalCancellations;
			}
			public Double getAverageBookingTime() {
				return averageBookingTime;
			}
			public void setAverageBookingTime(Double averageBookingTime) {
				this.averageBookingTime = averageBookingTime;
			}
			public Double getTrainOccupacencyrate() {
				return trainOccupacencyrate;
			}
			public void setTrainOccupacencyrate(Double trainOccupacencyrate) {
				this.trainOccupacencyrate = trainOccupacencyrate;
			}
	   
	   	   
}
