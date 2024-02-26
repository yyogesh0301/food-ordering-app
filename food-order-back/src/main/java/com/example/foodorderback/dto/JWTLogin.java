package com.example.foodorderback.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JWTLogin {
	public String username;
	public String role;

}
