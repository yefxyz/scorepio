package com.andre.core.repository;

import org.springframework.stereotype.Repository;

import com.andre.core.model.User;

@Repository(value = "userRedisDao")
public class UserRedisDaoImpl extends GenericRedisDaoImpl<User> {

}
