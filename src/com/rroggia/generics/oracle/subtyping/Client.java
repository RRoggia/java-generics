package com.rroggia.generics.oracle.subtyping;

import java.util.ArrayList;
import java.util.List;

public class Client {

	public static void main(String[] args) {

		ArrayList<Integer> integers = new ArrayList<>();
		integers.add(1);
		integers.add(2);

		Client.receiveListOfIntegerAndSubtypes(integers);
		Client.receiveListOfNumberAndSubtypes(integers);
		Client.receiveListAsWildcard(integers);

		ArrayList<Number> numbers = new ArrayList<>();
		numbers.add(3);
		numbers.add(4);

		Client.receiveListOfNumberAndSubtypes(numbers);
		Client.receiveListAsWildcard(numbers);

		Client.receiveNumberAndSupertypes(numbers);
		Client.receiveIntegerAndSupertypes(numbers);
		Client.receiveIntegerAndSupertypes(integers);

	}

	public static void receiveNumberAndSupertypes(List<? super Number> numbers) {
		System.out.println("Numbers and supertypes" + numbers);
		receiveIntegerAndSupertypes(numbers);
	}

	public static void receiveIntegerAndSupertypes(List<? super Integer> integers) {
		System.out.println("Integers and supertypes" + integers);
		receiveListAsWildcard(integers);
	}

	public static void receiveListOfIntegerAndSubtypes(List<? extends Integer> integers) {
		System.out.println("Integers and subtypes " + integers);
		receiveListOfNumberAndSubtypes(integers);
	}

	public static void receiveListOfNumberAndSubtypes(List<? extends Number> numbers) {
		System.out.println("Number and subtypes " + numbers);
		receiveListAsWildcard(numbers);
	}

	public static void receiveListAsWildcard(List<?> wildcards) {
		System.out.println("Wildcards " + wildcards);
	}

}
