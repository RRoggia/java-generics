package com.rroggia.generics.oracle.questions;

import java.util.Collection;
import java.util.function.Predicate;

public class Question1 {

	public static void main(String[] args) {

	}

	public static <T> int countNumberOfElement(Collection<T> collection, Predicate<T> predicate) {
		int i = 0;

		for (T elem : collection) {
			if (predicate.test(elem)) {
				i++;
			}
		}

		return i;
	}

}
