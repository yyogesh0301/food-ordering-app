package com.example.foodorderback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.foodorderback.dto.PasswordDTO;
import com.example.foodorderback.dto.UserDTO;
import com.example.foodorderback.model.User;
import com.example.foodorderback.service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/registration")
	public ResponseEntity<String> createUser(@RequestBody User user) {
		String responseToClient = userService.validateUser(user);
		if (!responseToClient.equals("valid")) {
			return ResponseEntity.ok(responseToClient);
		} else {
			responseToClient = userService.saveUser(user);
			return ResponseEntity.ok(responseToClient);
		}
	}

	@PostMapping("/createEmployee")
	public ResponseEntity<String> createEmployee(@RequestBody User user) {
		String responseToClient = userService.validateUser(user);
		if (!responseToClient.equals("valid")) {
			return ResponseEntity.ok(responseToClient);
		} else {
			responseToClient = userService.saveEmployee(user);
			return ResponseEntity.ok(responseToClient);
		}
	}

	@GetMapping("/getAllUsers")
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		List<UserDTO> usersDTO = userService.findAllUsers();
		return ResponseEntity.ok(usersDTO);
	}

	@GetMapping("/getAllEmployees")
	public ResponseEntity<List<UserDTO>> getAllEmployees() {
		List<UserDTO> employeesDTO = userService.findAllEmployees();
		return ResponseEntity.ok(employeesDTO);
	}

	@PutMapping("/updateUser")
	public ResponseEntity<String> updateUser(@RequestBody UserDTO userDTO) {
		String validationStatus = userService.validateUserUpdate(userDTO);
		if (!validationStatus.equals("valid")) {
			return ResponseEntity.ok(validationStatus);
		}
		String response = userService.updateUser(userDTO);
		return ResponseEntity.ok(response);
	}

	@PutMapping("/updateUserByIdAndDetails/{id}")
	public ResponseEntity<String> updateUserByIdAndDetails(@PathVariable Long id, @RequestBody User employeeDetails) {
		User user = userService.findOne(id);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		employeeDetails.setId(user.getId());
		employeeDetails.setRole(user.getRole());
		String validationStatus = userService.validateEmployeeUpdate(employeeDetails);
		if (!validationStatus.equals("valid")) {
			return ResponseEntity.ok(validationStatus);
		}
		String response = userService.updateEmployee(employeeDetails);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/getCurrentUser")
	public ResponseEntity<User> getCurrentUser() {
		User user = userService.getCurrentUser();
		return ResponseEntity.ok(user);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
		User user = userService.findOne(id);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(new UserDTO(user));
	}

	@PutMapping("/deactivateUser/{id}")
	public ResponseEntity<String> deactivateUser(@PathVariable Long id) {
		User user = userService.findOne(id);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		String response = userService.deactivateUser(id);
		return ResponseEntity.ok(response);
	}

	@PutMapping("/changePassword")
	public ResponseEntity<String> changePassword(@RequestBody PasswordDTO passwordDTO) {
		String responseToClient = userService.changePassword(passwordDTO);
		return ResponseEntity.ok(responseToClient);
	}
}
