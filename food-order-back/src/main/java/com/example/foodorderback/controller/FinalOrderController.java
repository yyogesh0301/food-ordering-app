package com.example.foodorderback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.foodorderback.dto.FinalOrderDTO;
import com.example.foodorderback.dto.FinalOrderIdAndStatusDTO;
import com.example.foodorderback.dto.ItemFromCartDTO;
import com.example.foodorderback.dto.OrderItemDTO;
import com.example.foodorderback.model.FinalOrder;
import com.example.foodorderback.model.User;
import com.example.foodorderback.service.FinalOrderService;
import com.example.foodorderback.service.OrderItemService;
import com.example.foodorderback.service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/finalOrder")
public class FinalOrderController {

	@Autowired
	FinalOrderService finalOrderService;

	@Autowired
	OrderItemService orderItemService;

	@Autowired
	UserService userService;

	@PostMapping(value = "/createFinalOrder", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Long> saveFinalOrderUser(@RequestBody OrderItemDTO orderItemDTO) {
		Long responseToClient = finalOrderService.makeFinalOrder(orderItemDTO);
		return ResponseEntity.ok(responseToClient);
	}

	@GetMapping(value = "/getFinalOrderById/{id}")
	public ResponseEntity<FinalOrder> getFinalOrderById(@PathVariable Long id) {
		FinalOrder finalOrder = finalOrderService.findOne(id);
		return ResponseEntity.ok(finalOrder);
	}

	@GetMapping(value = "/getAllDeliveredFinalOrders")
	public ResponseEntity<List<FinalOrderDTO>> getAllDeliveredFinalOrders() {
		List<FinalOrderDTO> allDeliveredFinalOrders = finalOrderService.getAllDeliveredFinalOrders();
		return ResponseEntity.ok(allDeliveredFinalOrders);
	}

	@GetMapping(value = "/getAllActiveFinalOrders")
	public ResponseEntity<List<FinalOrderDTO>> getAllActiveFinalOrders() {
		List<FinalOrderDTO> allActiveFinalOrders = finalOrderService.getAllActiveFinalOrders();
		return ResponseEntity.ok(allActiveFinalOrders);
	}

	@GetMapping(value = "/getAllMyActiveFinalOrders")
	public ResponseEntity<List<FinalOrderDTO>> getAllMyActiveFinalOrders() {
		User currentUser = userService.getCurrentUser();
		List<FinalOrderDTO> allMyActiveFinalOrders = finalOrderService.getAllMyActiveFinalOrders(currentUser.getId());
		return ResponseEntity.ok(allMyActiveFinalOrders);
	}

	@GetMapping(value = "/getAllMyDeliveredFinalOrders")
	public ResponseEntity<List<FinalOrderDTO>> getAllMyDeliveredFinalOrders() {
		User currentUser = userService.getCurrentUser();
		List<FinalOrderDTO> allMyDeliveredFinalOrders = finalOrderService
				.getAllMyDeliveredFinalOrders(currentUser.getId());
		return ResponseEntity.ok(allMyDeliveredFinalOrders);
	}

	@GetMapping(value = "/getOrderItemsByFinalOrderId/{id}")
	public ResponseEntity<List<ItemFromCartDTO>> getOrderItemsByFinalOrderId(@PathVariable Long id) {
		List<ItemFromCartDTO> itemsFromCartByFinalOrderId = orderItemService.getItemFromCartByFinalOrderId(id);
		return ResponseEntity.ok(itemsFromCartByFinalOrderId);
	}

	@PutMapping(value = "/setFinalOrderToDelivered/{finalOrderId}")
	public ResponseEntity<String> setFinalOrderToDelivered(@PathVariable Long finalOrderId) {
		String response = finalOrderService.setFinalOrderToDelivered(finalOrderId);
		return ResponseEntity.ok(response);
	}

	@PutMapping(value = "/changeStatus", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> editStatus(@RequestBody FinalOrderIdAndStatusDTO foIdStatus) {
		String response = finalOrderService.changeFinalOrderStatus(foIdStatus);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping(value = "/deleteFinalOrder/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		String responseToClient = finalOrderService.delete(id);
		return ResponseEntity.ok(responseToClient);
	}
}
