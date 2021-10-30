package com.datadog.springJmsExample.receiptProcessor;

import java.util.Date;

public class Receipt {
	
	private String user;
	private String userEmail;
	private Double total;
	private String items;
	private Date placedAt;
	private Date estimatedDeliveryTime;
	
	public Receipt(String user, String userEmail, Double total, String items, Date placedAt, Date estimatedDeliveryTime) {
		super();
		this.user = user;
		this.userEmail = userEmail;
		this.total = total;
		this.items = items;
		this.placedAt = placedAt;
		this.estimatedDeliveryTime = estimatedDeliveryTime;
	}

	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
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
		return "Receipt [user=" + user + ", userEmail=" + userEmail + ", total=" + total + ", items=" + items
				+ ", placedAt=" + placedAt + ", estimatedDeliveryTime=" + estimatedDeliveryTime + "]";
	}

}
