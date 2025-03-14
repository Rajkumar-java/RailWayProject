package com.railway.railway_dashboard_service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class KPIDTO 
{
	   @NotNull(message = "total booking cannot null")
	   @Min(value = 0, message = "total booking tiime must be 0")
       private Long totalBooking;
	   
	   @NotNull(message = "total revenue cannot null")
	   @Min(value = 0, message = "total revenue must be 0")
       private Double totalRevenue;
	   
	   @NotNull(message = "conversionrate cannot nll")
	   @Min(value = 0, message = "coversionrate must be 0")
       private Double conversionRate;
	   
	   @NotNull(message = "total cencelation cannot null")
	   @Min(value = 0, message = "total cencellation must be 0")
       private Long totalCencellation;
	   
	   @NotNull(message = "average booking time cannot null")
	   @Min(value = 0, message = "avarage booking time must be 0")
       private Double averageBookingtime;
	   
	   @NotNull(message = "train occupancy rate cannot null")
	   @Min(value = 0, message = "train occupancy rate must be 0")
       private Double trainOccupancyRate;
	
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
	
		public Long getTotalCencellation() {
			return totalCencellation;
		}
	
		public void setTotalCencellation(Long totalCencellation) {
			this.totalCencellation = totalCencellation;
		}
	
		public Double getAverageBookingtime() {
			return averageBookingtime;
		}
	
		public void setAverageBookingtime(Double averageBookingtime) {
			this.averageBookingtime = averageBookingtime;
		}
	
		public Double getTrainOccupancyRate() {
			return trainOccupancyRate;
		}
	
		public void setTrainOccupancyRate(Double trainOccupancyRate) {
			this.trainOccupancyRate = trainOccupancyRate;
		}
	   
	     
}