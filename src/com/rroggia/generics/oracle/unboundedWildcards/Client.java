package com.rroggia.generics.oracle.unboundedWildcards;

import java.util.Arrays;
import java.util.List;

public class Client {

	public static void main(String[] args) {

		List<Object> objects = Arrays.asList(new Object(), new Object());

		List<String> strings = Arrays.asList("Roggia", "Renan");

		printList(objects);
		printList(strings);

		printList1(objects);
		printList1(strings);

	}

	public static void printList(List<?> objects) {
		for (Object object : objects) {
			System.out.println(object);
		}
		System.out.println();
	}

	public static <T> void printList1(List<T> objects) {
		for (Object object : objects) {
			System.out.println(object);
		}
		System.out.println();
	}

}
