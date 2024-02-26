package com.example.foodorderback.controller;

import java.io.IOException;
import java.util.Base64;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.foodorderback.dto.MealTypeDTO;
import com.example.foodorderback.model.MealType;
import com.example.foodorderback.service.MealTypeService;
import com.google.gson.Gson;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/mealType")
public class MealTypeController {

	@Autowired
	MealTypeService mealTypeService;

	@GetMapping("/getAllMealTypes")
	public ResponseEntity<List<MealTypeDTO>> getAllMealTypeList() {
		List<MealTypeDTO> allMealTypeDTOList = mealTypeService.getAllMealTypes();
		return ResponseEntity.ok(allMealTypeDTOList);
	}

	@PostMapping("/createMealType")
	public ResponseEntity<String> createMeal(@RequestParam("image") MultipartFile image,
			@RequestParam("mealType") String mealTypeJson) {
		MealType mealType = new Gson().fromJson(mealTypeJson, MealType.class);

		String responseToClient = mealTypeService.isValidInput(mealType);
		if (responseToClient.equals("valid")) {
			try {
				mealType.setImage(Base64.getEncoder().encodeToString(image.getBytes()));
				mealType.setImageName(image.getOriginalFilename());
				responseToClient = mealTypeService.save(mealType);
				return ResponseEntity.ok(responseToClient);
			} catch (IOException e) {
				return ResponseEntity.ok("fail");
			}
		} else {
			return ResponseEntity.ok("invalid");
		}
	}

	@PutMapping("/updateMealType")
	public ResponseEntity<String> editMealType(@RequestBody MealType mealType) {
		String response = mealTypeService.editMealType(mealType);
		return ResponseEntity.ok(response);
	}

	@PutMapping("/deleteMealType/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		String responseToClient = mealTypeService.delete(id);
		return ResponseEntity.ok(responseToClient);
	}
}
