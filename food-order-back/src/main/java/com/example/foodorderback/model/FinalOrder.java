package com.example.foodorderback.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
