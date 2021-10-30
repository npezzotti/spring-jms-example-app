package com.datadog.springJmsExample.repositories;

import org.springframework.data.repository.CrudRepository;

import com.datadog.springJmsExample.domain.OrderItem;

public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {

}
