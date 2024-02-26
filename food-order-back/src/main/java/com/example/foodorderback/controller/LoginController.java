package com.example.foodorderback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.foodorderback.dto.LoginDTO;
import com.example.foodorderback.model.Login;
import com.example.foodorderback.service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class LoginController {

	@Autowired
	private UserService userService;

	@GetMapping("/welcomeTest")
	public String welcome() {
		return "test!!!";
	}

	@PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoginDTO> generateToken(@RequestBody Login login) {
		try {
			LoginDTO loginDTO = userService.generateToken(login);
			return ResponseEntity.ok(loginDTO);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

	@GetMapping("/logout")
	public ResponseEntity<String> logout() {
		try {
			String responseToClient = userService.isValidLogout();
			return ResponseEntity.ok(responseToClient);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}
}
