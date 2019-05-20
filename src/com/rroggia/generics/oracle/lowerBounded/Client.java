package com.rroggia.generics.oracle.lowerBounded;

import java.util.ArrayList;
import java.util.List;

public class Client {

	public static void main(String[] args) {

		List<Object> listOfObjects = new ArrayList<>();
		addIntegerAndItsSupertypes(listOfObjects);
		System.out.println("Objects: " + listOfObjects);

		List<Number> listOfNumbers = new ArrayList<>();
		addIntegerAndItsSupertypes(listOfNumbers);
		System.out.println("Numbers: " + listOfNumbers);

		List<Integer> listOfInteger = new ArrayList<>();
		addIntegerAndItsSupertypes(listOfInteger);
		System.out.println("Integers: " + listOfInteger);

	}

	public static void addIntegerAndItsSupertypes(List<? super Integer> integers) {
		for (int i = 1; i <= 10; i++) {
			integers.add(i);
		}
	}
}
