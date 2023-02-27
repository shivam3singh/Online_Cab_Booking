package com.urbancab.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer extends AbstractUser{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Integer customerId;

	@JsonIgnore
//	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy ="customer" )
	private List<TripBooking> trips=new ArrayList<>();
	
	
}
