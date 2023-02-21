package com.urbancab.model;

import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class AbstractUser {

	private String username;
	private String password;
	private String address;
	private String mobileNumber;
	private String email;
}
