package com.rroggia.generics.javaeffective.item32;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Client {

	public static void main(String[] args) {
		try {
			new Client().dangerous(Arrays.asList("aaa"));
		} catch (Exception e) {
			System.out.println("exception thrown due to heap pollution");
		}

		try {
			String[] attributes = pickTwo("Good", "Fast", "Cheap");
		} catch (Exception e) {
			System.out.println(
					"exception thrown due to the casting of an Object[] to a String[]. Since the toArray is non-reifiable the vararg is an Object[]");
		}

		List<String> flatten = flatten(Arrays.asList("a", "b"), Arrays.asList("c"));
		System.out.println("flatten using typesafe method: " + flatten);

		List<String> flatten2 = flatten(Arrays.asList(Arrays.asList("a", "b"), Arrays.asList("c")));
		System.out.println("flatten using List<List<?>>: " + flatten2);

		List<String> pickTwoTypeSafe = pickTwoTypeSafe("Good", "Fast", "Cheap");
		System.out.println("pickTwoTypeSafe: " + pickTwoTypeSafe);
	}

	public <T> void getParameters(T... t) {
	}

	public void dangerous(List<String>... stringLists) {
		List<Integer> intList = Arrays.asList(42);
		Object[] objects = stringLists;
		objects[0] = intList;
		String s = stringLists[0].get(0); // Heap pollution ClassCastException
	}

	// UNSAFE - exposes a reference to its generic parameter
	public static <T> T[] toArray(T... args) {
		return args;
	}

	public static <T> T[] pickTwo(T a, T b, T c) {
		switch (ThreadLocalRandom.current().nextInt(3)) {
		case 0:
			return toArray(a, b);
		case 1:
			return toArray(a, c);
		case 2:
			return toArray(b, c);
		}

		throw new AssertionError();
	}

	@SafeVarargs
	public static <T> List<T> flatten(List<? extends T>... lists) {
		List<T> results = new ArrayList<>();
		for (List<? extends T> list : lists) {
			results.addAll(list);
		}
		return results;
	}

	// even better change SafeVarargs to List
	public static <T> List<T> flatten(List<List<? extends T>> lists) {
		List<T> results = new ArrayList<>();
		for (List<? extends T> list : lists) {
			results.addAll(list);
		}
		return results;
	}

	public static <T> List<T> pickTwoTypeSafe(T a, T b, T c) {
		switch (ThreadLocalRandom.current().nextInt(3)) {
		case 0:
			return Arrays.asList(a, b);
		case 1:
			return Arrays.asList(a, c);
		case 2:
			return Arrays.asList(b, c);
		}

		throw new AssertionError();
	}

}
