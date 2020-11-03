package bran.packages.restaurant.dto;

import java.sql.Date;
import java.sql.Time;

import lombok.Data;

@Data
public class RestaurantDTO {

	private Long id;
	
	private String name;
	
	private String address;
	
	private Time startTime;
	
	private Time endTime;

	private Date lastChange;
	
	
}
