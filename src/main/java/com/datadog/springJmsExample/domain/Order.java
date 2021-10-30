package com.datadog.springJmsExample.domain;

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
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
    @ManyToOne(optional = false)
	private User user;
	private String body;
	@OneToMany(
			mappedBy = "order",
			fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
            )
	@JsonIgnore
	private List<OrderItem> items;
	private Double total;
	@CreationTimestamp
	private Date placedAt;
	
	public Order() {
	}

	public Long getid() {
		return id;
	}

	public void setid(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getBody() {
		return body;
	}
	
	public void setBody(String body) {
		this.body = body;
	}
	
	public List<OrderItem> getItems() {
		return items;
	}
	
	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Date getPlacedAt() {
		return placedAt;
	}
	
	public void setPlacedAt(Date placedAt) {
		this.placedAt = placedAt;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", user=" + user + ", body=" + body + ", placedAt=" + placedAt + "]";
	}

	
}