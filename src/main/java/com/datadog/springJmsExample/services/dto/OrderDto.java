package com.datadog.springJmsExample.services.dto;

import javax.validation.constraints.NotNull;

import com.datadog.springJmsExample.domain.Order;

public class OrderDto {

	private Long id;
	@NotNull
	private Long userId;
	
	public OrderDto(Order order) {
		this.setId(order.getid());
		this.setUserId(order.getUser().getId());
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
}
