package com.andre;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.andre.core.model.User;
import com.andre.core.repository.GenericRedisDao;
import com.andre.core.repository.jpa.UserRepository;
import com.andre.core.util.IdUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScorepioApplicationTests {

	protected Logger logger = LogManager.getLogger(getClass());

	@Autowired
	private GenericRedisDao<User> userRedisDao;

	@Autowired
	private UserRepository userRepository;

	// @Test
	public void contextLoads() {
	}

	@Test
	// @Transactional
	// @Rollback(value = false)
	public void testRedis() {
		User user01 = new User("test01", "123456");
		user01.setId(IdUtil.nextID());
		user01.setFirstName("Andre");
		user01.setLastName("Ye");
		// redisUserTemplate.opsForValue().set(user01.getUsername(), user01);
		// User user02 = redisUserTemplate.opsForValue().get(user01.getUsername());

		// redisBaseObjectTemplate.opsForValue().set(user01.getUsername(), user01);
		// System.out.println(redisBaseObjectTemplate.opsForValue().get(user01.getUsername()));

		userRedisDao.save(user01);
		// 若开启事务，这一条是查询不到的。
		User user02 = userRedisDao.find(user01.getRedisKey());

		if (user02 != null) {
			logger.info("un: " + user02.getUsername() + " pwd: " + user02.getPassword());

			System.out.println("class name: " + user02.getClass().getName());
			System.out.println("class simple name: " + user02.getClass().getSimpleName());
		} else {
			System.out.println("Can not find data.");
		}
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

	// @Test
	public void testBaseDao() {
		User user01 = new User("test01", "123456");

	}

}
