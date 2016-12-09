package com.andre;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.andre.core.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScorepioApplicationTests {

	@Autowired
	private RedisTemplate<String, User> redisUserTemplate;

	// @Test
	public void contextLoads() {
	}

	@Test
	public void testRedis() {
		User user01 = new User(123_456_789L, "test01");
		redisUserTemplate.opsForValue().set(user01.getId().toString(), user01);

		System.out.println(redisUserTemplate.opsForValue().get(user01.getId().toString()).getUsername());
	}

}
