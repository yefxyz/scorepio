package com.andre.test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

import com.andre.core.model.BaseObject;

public class GenericTest01<K, V> {

	public static void main(String[] args) {
		// 匿名内部类。
		GenericTest01<String, BaseObject> test1 = new GenericTest01<String, BaseObject>() {
		};
		GenericTest01<String, BaseObject> test2 = new GenericTest01<>();

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
