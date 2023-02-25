package com.urbancab.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class Cab {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Integer cabId;

	@NotNull(message = "Cab type should not be null.")
	private String cabType;

	@NotNull(message = "Per Km Rate should not be null.")
	private Float perKmRate;

	@JsonIgnore
	private Boolean available = true;

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	private Driver driver;
}
