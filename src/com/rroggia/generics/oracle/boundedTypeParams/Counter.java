package com.rroggia.generics.oracle.boundedTypeParams;

public class Counter {

	public static <T extends Comparable<T>> int countGreaterThan(T[] anArray, T elem) {
		int count = 0;

		for (T e : anArray) {
			// if (e > elem) compile error

			if (e.compareTo(elem) > 0)
				++count;
		}

		return count;
	}

}
