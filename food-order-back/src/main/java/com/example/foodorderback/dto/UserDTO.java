package com.example.foodorderback.dto;

import com.example.foodorderback.model.Role;
import com.example.foodorderback.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

	private Long id;

	private String firstName;
	private String lastName;
	private String email;
	private String username;
	private String password;
	private Role role;
	private String address;
	private String phoneNumber;
	private boolean deleted;

	public UserDTO(User user) {
		this(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getUsername(),
				user.getPassword(), user.getRole(), user.getAddress(), user.getPhoneNumber(), user.isDeleted());
	}

}
