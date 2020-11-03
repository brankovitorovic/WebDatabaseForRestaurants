package bran.packages.restaurant.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import jdk.jfr.Timespan;
import lombok.Data;

@Data
@Entity
@Table(name = "restaurants")
public class Restaurant implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, nullable = false, length = 50)
	private String name;
	
	@Column(unique = false,nullable = true, length = 50)
	private String address;
	
	@Type(type = "time")
	@Column
	private Time startTime;
	
	@Type(type = "time")
	@Column
	private Time endTime;
	
	@Type(type = "date")
	@Column
	private Date lastChange;

	
	
	
}
