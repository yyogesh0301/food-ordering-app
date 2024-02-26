package com.example.foodorderback.dto;

import java.util.List;

import lombok.Data;

@Data
public class OrderItemDTO {

	private List<ItemFromCartDTO> itemsFromCart;
	private String address;
	private String phoneNumber;

	private int finalPrice;

}
