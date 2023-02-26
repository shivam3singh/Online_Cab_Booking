package com.urbancab.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Driver extends AbstractUser{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Integer driverId;

	@NotNull(message = "Licence no should not be null.")
	private String licenceNo;

	@OneToOne(mappedBy = "driver")
	@JsonIgnore
	private Cab cab;

	@NotNull(message = "Rating should not be null.")
	@Min(value = 0, message = "Rating should not be less than 0.")
	@Max(value = 5, message = "Rating should not be more than 5.")
	private Float rating;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Boolean available = true;
	@JsonIgnore
	@OneToMany(mappedBy = "driver")
	private List<TripBooking> trips = new ArrayList<>();
}
