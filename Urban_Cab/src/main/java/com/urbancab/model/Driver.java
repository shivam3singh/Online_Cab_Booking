package com.urbancab.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Driver extends AbstractUser{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Integer driverId;
	private String licenceNo;
	
	@OneToOne(mappedBy = "driver")
	private Cab cab;
	private Float rating;
	
	@OneToMany(mappedBy = "driver")
	private List<TripBooking> trips = new ArrayList<>();
}
