package com.datadog.springJmsExample.receiptProcessor;

import java.util.Date;

import javax.persistence.ManyToOne;

import com.datadog.springJmsExample.domain.User;

public class Receipt {
	
	@ManyToOne(optional = false)
	private User user;
	private Double total;
	private String items;
	private Date placedAt;
	private Date estimatedDeliveryTime;
	
	public Receipt(User user, Double total, String items, Date placedAt, Date estimatedDeliveryTime) {
		super();
		this.user = user;
		this.total = total;
		this.items = items;
		this.placedAt = placedAt;
		this.estimatedDeliveryTime = estimatedDeliveryTime;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public String getItems() {
		return items;
	}
	public void setItems(String items) {
		this.items = items;
	}
	public Date getPlacedAt() {
		return placedAt;
	}
	public void setPlacedAt(Date placedAt) {
		this.placedAt = placedAt;
	}
	public Date getEstimatedDeliveryTime() {
		return estimatedDeliveryTime;
	}
	public void setEstimatedDeliveryTime(Date estimatedDeliveryTime) {
		this.estimatedDeliveryTime = estimatedDeliveryTime;
	}

	@Override
	public String toString() {
		return "Receipt [user=" + user + ", total=" + total + ", items=" + items
				+ ", placedAt=" + placedAt + ", estimatedDeliveryTime=" + estimatedDeliveryTime + "]";
	}

}
