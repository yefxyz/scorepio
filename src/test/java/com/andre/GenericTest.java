package com.andre;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

import com.andre.core.model.BaseObject;

public class GenericTest<K, V> {

	public static void main(String[] args) {
		// 匿名内部类。
		GenericTest<String, BaseObject> test1 = new GenericTest<String, BaseObject>() {
		};
		GenericTest<String, BaseObject> test2 = new GenericTest<>();

		Type type1 = test1.getClass().getGenericSuperclass();
		System.out.println("type1: " + type1);
		Type type2 = test2.getClass().getGenericSuperclass();
		System.out.println("type2: " + type2);

		if (type1 instanceof ParameterizedType) {
			Type[] actualTypes = ((ParameterizedType) type1).getActualTypeArguments();
			Arrays.stream(actualTypes).forEach(System.out::println);
		} else {
			System.out.println("Not a generic type");
		}

		if (type2 instanceof ParameterizedType) {
			Type[] actualTypes = ((ParameterizedType) type2).getActualTypeArguments();
			Arrays.stream(actualTypes).forEach(System.out::println);
		} else {
			System.out.println("Not a generic type");
		}

	}

}