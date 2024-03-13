package com.example.foodorderback.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class FinalOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private User user;

	@JsonIgnore
	@OneToMany(mappedBy = "finalOrder", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<OrderItem> orders = new ArrayList<>();

	private Date date;
	private int finalPrice;
	private String address;
	private String phoneNumber;
	private String status;

	public FinalOrder(User user, Date date, int finalPrice, String address, String phoneNumber, String status) {
		this.user = user;
		this.date = date;
		this.finalPrice = finalPrice;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.status = status;
	}
}