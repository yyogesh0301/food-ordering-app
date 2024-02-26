package com.example.foodorderback.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.foodorderback.dto.MealDTO;
import com.example.foodorderback.model.Meal;
import com.example.foodorderback.service.MealService;
import com.google.gson.Gson;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/meal")
public class MealController {

	@Autowired
	MealService mealService;

	@GetMapping("/getAllMeals")
	public ResponseEntity<List<MealDTO>> getAllMeals() {
		List<MealDTO> allMealDTOList = mealService.findAll();
		return ResponseEntity.ok(allMealDTOList);
	}

	@GetMapping("/getMealsByMealTypeId/{id}")
	public ResponseEntity<List<MealDTO>> getMealsByMealTypeId(@PathVariable Long id) {
		List<MealDTO> mealsByMealTypeId = mealService.getMealsByMealTypeId(id);
		return ResponseEntity.ok(mealsByMealTypeId);
	}

	@PostMapping("/createMeal")
	public ResponseEntity<String> createMeal(@RequestParam("image") MultipartFile image,
			@RequestParam("meal") String mealJson) {
		Meal meal = new Gson().fromJson(mealJson, Meal.class);

		String responseToClient = mealService.isValidInput(meal);
		if (!responseToClient.equals("valid")) {
			return ResponseEntity.ok(responseToClient);
		} else {
			try {
				meal.setImage(Base64.getEncoder().encodeToString(image.getBytes()));
				meal.setImageName(image.getOriginalFilename());
				responseToClient = mealService.save(meal);
				return ResponseEntity.ok(responseToClient);
			} catch (IOException e) {
				return ResponseEntity.ok("fail");
			}
		}
	}

	@PutMapping("/updateMeal")
	public ResponseEntity<String> editMeal(@RequestBody Meal meal) {
		String response = mealService.editMeal(meal);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/deleteMeal/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		String responseToClient = mealService.delete(id);
		return ResponseEntity.ok(responseToClient);
	}
}
