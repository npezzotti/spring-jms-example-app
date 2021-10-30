package com.datadog.springJmsExample.repositories;

import org.springframework.data.repository.CrudRepository;

import com.datadog.springJmsExample.domain.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
