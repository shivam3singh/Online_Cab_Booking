package com.urbancab.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class AbstractUser {

	@NotNull(message = "Username should not be null.")
	@NotBlank(message = "Username should not be blank.")
	@NotEmpty(message = "Username should not be empty.")
	@Column(unique = true)
	@Size(min = 5, max = 12, message="Username should be between 5 to 12 characters")
	private String username;

	@NotNull(message = "Password should not be null.")
	@NotBlank(message = "Password should not be blank.")
	@NotEmpty(message = "Password should not be empty.")
	@Size(min = 8,message="Password should be minimum of 8 characters")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;

	@NotNull(message = "Address should not be null.")
	@NotBlank(message = "Address should not be blank.")
	@NotEmpty(message = "Address should not be empty.")
	private String address;

	@NotNull(message = "Mobile number should not be null.")
	@Column(unique = true)
	@Pattern(regexp = "[6789][0-9]{9}", message = "Enter valid 10 digit mobile number")
	private String mobileNumber;

	@NotNull(message = "Email should not be null.")
	@Email(message = "Email address is not valid.")
	private String email;
}
