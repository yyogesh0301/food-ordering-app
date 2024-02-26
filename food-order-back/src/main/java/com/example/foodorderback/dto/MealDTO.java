package com.example.foodorderback.dto;

import com.example.foodorderback.model.Meal;
import com.example.foodorderback.model.MealType;

import lombok.Data;

@Data
public class MealDTO {

	public Long id;
	private MealType mealType;
	// 	private String mealTypeName;
	private String name;
	private int price;
	private String image;
	private String imageName;
	private String description;

	public MealDTO() {

	}

	public MealDTO(Meal meal) {
		// 		this(meal.getId(), meal.getMealType(), meal.getName(), meal.getPrice(), meal.getImage(), meal.getImageName());
		this.id = meal.getId();
		// 		this.mealTypeName = meal.getMealType().getTypeName();
		this.mealType = meal.getMealType();
		this.name = meal.getName();
		this.price = meal.getPrice();
		this.image = meal.getImage();
		this.imageName = meal.getImageName();
		this.description = meal.getDescription();
	}

}
