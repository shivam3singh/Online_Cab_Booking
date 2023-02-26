package com.urbancab.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class TripBooking {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Integer tripBookingId;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	private Driver driver;
	@NotNull(message = "From location should not be null.")
	private String fromLocation;

	@NotNull(message = "To location should not be null.")
	private String toLocation;

	@NotNull(message = "From date&time should not be null.")
	private LocalDateTime fromDateTime;

	@NotNull(message = "To date&time should not be null.")
	private LocalDateTime toDateTime;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Boolean status;

	@NotNull(message = "Distance should not be null.")
	@Min(value = 50, message = "Min distance should be 50")
	private Float distanceInKm;

	@JsonIgnore
	private Float Bill;

	@JsonIgnore
	@ManyToOne
	private Customer customer;
}
