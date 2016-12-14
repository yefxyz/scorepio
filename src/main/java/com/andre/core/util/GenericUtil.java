package com.andre.core.util;

import java.lang.reflect.Type;

import com.google.gson.reflect.TypeToken;

public class GenericUtil {

	public static Type getObjectGenericType(Object obj) {
		TypeToken typeToken = TypeToken.get(obj.getClass());
		return typeToken.getType();
	}

	public static void main(String[] args) {
	}

}
