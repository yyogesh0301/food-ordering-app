package com.example.foodorderback.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FinalOrderIdAndStatusDTO {

	public Long activeOrderId;
	public String status;

}
