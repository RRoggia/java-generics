package com.rroggia.generics.oracle.upperBounded;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Client {

	public static void main(String[] args) {
		List<Integer> a1 = new ArrayList<>();
		List<Number> a2 = new ArrayList<>();

		processList(a1, a2);

		List<Integer> integers = Arrays.asList(1, 2, 3);
		List<Double> doubles = Arrays.asList(2.0, 2.3, 3.5);

		System.out.printf("sum %f\n", sumOfList(integers));
		System.out.printf("sum %f\n", sumOfList(doubles));

		System.out.printf("sum %f\n", sumOfLists(integers));
		System.out.printf("sum %f\n", sumOfLists(doubles));

	}

	public static void processList(List<? extends Number> list1, List<? extends Number> list2) {

	}

	public static double sumOfList(List<? extends Number> list) {
		double d = 0.0;

		for (Number number : list) {
			d += number.doubleValue();
		}

		return d;
	}

	public static <T extends Number> double sumOfLists(List<T> list) {
		double d = 0.0;

		for (Number number : list) {
			d += number.doubleValue();
		}

		return d;
	}

}
