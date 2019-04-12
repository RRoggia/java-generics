package com.rroggia.generics.oracle.genTypeInference;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Client {

	static <T> T pick(T a1, T a2) {
		return a2;
	}

	public static void main(String[] args) {
		Reader reader = pick(new Developer(), new Person());
		Writer writer = pick(new Developer(), new Person());
		Object object = pick(new Developer(), new Person());

		reader.read();
		writer.write();
		object.toString();

		System.out.println(
				"Because both classes implements Writer and Reader interfaces, the most specific Type is considered the Object");
		System.out.println("By removing one of the interfaces, the Type is evaluated as the remaining interface");

		System.out.println();
		System.out.println();

		System.out.println("Not using type inference: verbose:");
		Map<String, List<String>> verbose = new HashMap<String, List<String>>();

		System.out.println("Using type inference: less verbose");
		Map<String, List<String>> lessVerbose = new HashMap<>();

		System.out.println();
		System.out.println();

		System.out.println("Type inference in the constructor:");
		System.out.println("X is an integer, while T is a string");
		MyClass<Integer> myClass = new MyClass<>("");

		System.out.println();
		System.out.println();
		List<String> listOne = Collections.emptyList();
		processStringList(Collections.emptyList());

	}

	static void processStringList(List<String> stringList) {
		// process stringList
	}

}
