package com.urbancab.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Cab {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer cabId;
	private String carType;
	private Float perKmRate;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Driver driver;
}
