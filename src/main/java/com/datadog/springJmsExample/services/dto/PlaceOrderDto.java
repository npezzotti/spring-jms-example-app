package com.datadog.springJmsExample.services.dto;

import javax.validation.constraints.NotNull;

import com.datadog.springJmsExample.domain.Order;

public class PlaceOrderDto {

	private Long id;
	@NotNull
	private Long userId;
	private Double totalPrice;
	
    public PlaceOrderDto() {
    }
	
	public PlaceOrderDto(Order order) {
		this.setId(order.getid());
		this.setUserId(order.getUser().getId());
		this.setTotalPrice(order.getTotal());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
}
