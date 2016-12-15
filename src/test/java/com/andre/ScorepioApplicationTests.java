package com.andre;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.andre.core.model.User;
import com.andre.core.repository.BaseDao;
import com.andre.core.repository.GenericRedisDao;
import com.andre.core.repository.jpa.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScorepioApplicationTests {

	@Autowired
	private GenericRedisDao<User> userRedisDao;

	@Autowired
	private UserRepository userRepository;

	// @Test
	public void contextLoads() {
	}

	@Test
	public void testRedis() {
		User user01 = new User("test01", "123456");
		user01.setFirstName("Andre");
		user01.setLastName("Ye");
		// redisUserTemplate.opsForValue().set(user01.getUsername(), user01);
		// User user02 = redisUserTemplate.opsForValue().get(user01.getUsername());

		// redisBaseObjectTemplate.opsForValue().set(user01.getUsername(), user01);
		// System.out.println(redisBaseObjectTemplate.opsForValue().get(user01.getUsername()));

		userRedisDao.saveData(user01);
		User user02 = userRedisDao.findData("test01");

		System.out.println("un: " + user02.getUsername() + " pwd: " + user02.getPassword());

		System.out.println("class name: " + user02.getClass().getName());
		System.out.println("class simple name: " + user02.getClass().getSimpleName());
	}

	// @Test
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

	@Test
	public void testDao() {
		User user01 = new User("test01", "123456");
		BaseDao.test(user01);
	}

}
