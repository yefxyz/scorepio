package com.andre;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.andre.core.model.User;
import com.andre.core.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScorepioApplicationTests {

	@Autowired
	private RedisTemplate<String, User> redisUserTemplate;

	@Autowired
	private UserRepository userRepository;

	// @Test
	public void contextLoads() {
	}

	@Test
	public void testRedis() {
		User user01 = new User("test01", "123456");
		redisUserTemplate.opsForValue().set(user01.getUsername(), user01);

		System.out.println(redisUserTemplate.opsForValue().get(user01.getUsername()));
	}

	@Test
	public void testJPA() {
		// 当首次测试，创建10条记录。
		// userRepository.save(new User("AAA", "123456"));
		// userRepository.save(new User("BBB", "123456"));
		// userRepository.save(new User("CCC", "123456"));
		// userRepository.save(new User("DDD", "654321"));
		// userRepository.save(new User("EEE", "123456"));
		// userRepository.save(new User("FFF", "123456"));
		// userRepository.save(new User("GGG", "123456"));
		// userRepository.save(new User("HHH", "123456"));
		// userRepository.save(new User("III", "123456"));
		// userRepository.save(new User("JJJ", "123456"));

		Assert.assertEquals(10, userRepository.findAll().size());

		User user01 = userRepository.findByUsername("DDD");
		Assert.assertEquals(user01.getPassword(), "654321");
	}

}
