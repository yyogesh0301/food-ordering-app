package com.example.foodorderback.dto;

import com.example.foodorderback.model.OrderItem;

public class ItemFromCartDTO {

	private Long mealId;
	private String mealName;
	private String mealTypeName;
	private String mealDescription;
	private String mealImage;
	private String mealImageName;
	private int mealPrice;

	private int quantity;

	public ItemFromCartDTO() {

	}

	public ItemFromCartDTO(OrderItem orderItem) {
		this.mealId = orderItem.getMeal().getId();
		this.mealName = orderItem.getMealName();
		this.mealTypeName = orderItem.getMealTypeName();
		this.mealDescription = orderItem.getMealDescription();

		this.mealImage = orderItem.getMeal().getImage();
		this.mealImageName = orderItem.getMealImageName();
		this.mealPrice = orderItem.getMealPrice();

		this.quantity = orderItem.getQuantity();
	}
	//
	//	public Meal getMeal() {
	//		return meal;
	//	}
	//
	//	public void setMeal(Meal meal) {
	//		this.meal = meal;
	//	}

	//	public MealFromCartDTO getMealFromCartDTO() {
	//		return mealFromCartDTO;
	//	}
	//
	//	public void setMealFromCartDTO(MealFromCartDTO mealFromCartDTO) {
	//		this.mealFromCartDTO = mealFromCartDTO;
	//	}

	public int getQuantity() {
		return quantity;
	}

	public Long getMealId() {
		return mealId;
	}

	public void setMealId(Long mealId) {
		this.mealId = mealId;
	}

	public String getMealName() {
		return mealName;
	}

	public void setMealName(String mealName) {
		this.mealName = mealName;
	}

	public String getMealTypeName() {
		return mealTypeName;
	}

	public void setMealTypeName(String mealTypeName) {
		this.mealTypeName = mealTypeName;
	}

	public String getMealDescription() {
		return mealDescription;
	}

	public void setMealDescription(String mealDescription) {
		this.mealDescription = mealDescription;
	}

	public String getMealImage() {
		return mealImage;
	}

	public void setMealImage(String mealImage) {
		this.mealImage = mealImage;
	}

	public String getMealImageName() {
		return mealImageName;
	}

	public void setMealImageName(String mealImageName) {
		this.mealImageName = mealImageName;
	}

	public int getMealPrice() {
		return mealPrice;
	}

	public void setMealPrice(int mealPrice) {
		this.mealPrice = mealPrice;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
