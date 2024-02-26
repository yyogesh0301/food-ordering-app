package com.example.foodorderback.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEditDTO {

	private Long id;

	private String firstName;
	private String lastName;
	private String username;
	private String email;

	private String phoneNumber;
	private String address;

}
