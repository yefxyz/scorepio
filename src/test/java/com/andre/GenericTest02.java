package com.andre;

import java.lang.reflect.Type;

import com.andre.core.model.User;
import com.google.common.reflect.TypeToken;

public class GenericTest02<T> {

	@SuppressWarnings("serial")
	public TypeToken<T> typeToken = new TypeToken<T>(this.getClass()) {
	};

	public static void main(String[] args) {
		GenericTest02<User> test = new GenericTest02<User>() {
		};
		Type type = test.typeToken.getType();
		System.out.println(type);
	}

}
