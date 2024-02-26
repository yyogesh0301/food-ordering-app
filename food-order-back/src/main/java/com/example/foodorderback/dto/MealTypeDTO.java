package com.example.foodorderback.dto;

import com.example.foodorderback.model.MealType;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MealTypeDTO {

	private Long id;
	private String typeName;
	private String image;
	private String imageName;
	private String description;

	public MealTypeDTO() {

	}

	public MealTypeDTO(MealType mealType) {
		this(mealType.getId(), mealType.getTypeName(), mealType.getImage(), mealType.getImageName(),
				mealType.getDescription());
	}

}
