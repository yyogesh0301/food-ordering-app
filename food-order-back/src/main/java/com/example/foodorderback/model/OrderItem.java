package com.example.foodorderback.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	private Meal meal;

	private String mealName;

	// Consider using BigDecimal instead of int for price
	private int mealPrice;

	private String mealDescription;

	@Lob
	private String mealImage;

	private String mealImageName;

	private String mealTypeName;

	@ManyToOne
	private FinalOrder finalOrder;

	private int quantity;

	// Constructor, getters, and setters are automatically generated by Lombok
}
