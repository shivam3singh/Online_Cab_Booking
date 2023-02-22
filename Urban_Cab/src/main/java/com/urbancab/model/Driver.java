package com.urbancab.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Driver {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer driverId;
	private String licenceNo;
	
	@OneToOne(mappedBy = "driver")
	private Cab cab;
	private Float rating;
	
	@OneToOne
	private TripBooking trip;
}
