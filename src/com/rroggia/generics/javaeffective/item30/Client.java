package com.rroggia.generics.javaeffective.item30;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Client {

	public static void main(String[] args) {
		System.out.println("Generic method implementation");
		Set<String> guys = Stream.of("Tom", "Dick", "Harry").collect(Collectors.toSet());
		Set<String> stooges = Stream.of("Larry", "Moe", "Curly").collect(Collectors.toSet());

		Set<String> aflCio = unionGeneric(guys, stooges);
		System.out.println(aflCio);

		System.out.println();

		System.out.println("Generic singleton factory implementation");

		String[] strings = { "jute", "hemp", "nylon" };
		UnaryOperator<String> stringIdentityFunction = identityFunction();
		for (String s : strings)
			System.out.println(stringIdentityFunction.apply(s));

		Number[] numbers = { 1, 2.0, 3l };
		UnaryOperator<Number> numberIdentityFunction = identityFunction();
		for (Number n : numbers)
			System.out.println(numberIdentityFunction.apply(n));

		System.out.println();

		System.out.println("Recursive type bound");

	}

	public static Set union(Set s1, Set s2) {
		Set result = new HashSet(s1);
		result.addAll(s2);
		return result;
	}

	public static <E> Set<E> unionGeneric(Set<E> s1, Set<E> s2) {
		HashSet<E> result = new HashSet<>(s1);
		result.addAll(s2);
		return result;
	}

	private static UnaryOperator<Object> IDENTITY_FN = (t) -> t;

	// identity function returns its argument unmodified, so we know that it is
	// typesafe
	@SuppressWarnings("unchecked")
	public static <T> UnaryOperator<T> identityFunction() {
		return (UnaryOperator<T>) IDENTITY_FN;
	}

	public static <E extends Comparable<E>> E max(Collection<E> c) {
		if (c.isEmpty())
			throw new IllegalArgumentException("Empty Collection");

		E result = null;

		for (E e : c) {
			if (result == null || e.compareTo(result) > 0)
				result = Objects.requireNonNull(e);
		}

		return result;
	}

}
