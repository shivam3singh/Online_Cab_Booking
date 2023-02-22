package com.urbancab.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class AbstractUser {

	@NotNull
	@NotBlank
	@NotEmpty
	@Column(unique = true)
	@Size(min = 5, max = 12, message="Username should be between 5 to 12 characters")
	private String username;

	@NotNull
	@NotBlank
	@NotEmpty
	@Size(min = 8,message="Password should be minimum of 8 characters")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;

	@NotNull
	@NotBlank
	@NotEmpty
	private String address;

	@NotNull
	@Column(unique = true)
	@Pattern(regexp = "[6789][0-9]{9}", message = "Enter valid 10 digit mobile number")
	private String mobileNumber;

	@NotNull
	@Email(message = "Email address is not valid.")
	private String email;
}
