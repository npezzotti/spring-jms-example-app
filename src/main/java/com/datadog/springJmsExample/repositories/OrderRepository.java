package com.datadog.springJmsExample.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.datadog.springJmsExample.domain.Order;
import com.datadog.springJmsExample.domain.User;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
	List<Order> findByUser(User user);
}
