package com.datadog.springJmsExample.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.datadog.springJmsExample.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
