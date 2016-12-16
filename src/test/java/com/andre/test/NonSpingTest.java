package com.andre.test;

import org.junit.Assert;
import org.junit.Test;

public class NonSpingTest {

	@Test
	public void testSubString() {
		String s1 = "User::123456789";
		int idx = s1.indexOf("::");
		String s2 = s1.substring(idx + 2);
		Assert.assertEquals(s2, "123456789");
	}
	
}
