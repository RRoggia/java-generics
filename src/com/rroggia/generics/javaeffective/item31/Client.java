package com.rroggia.generics.javaeffective.item31;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Client {
	public static void main(String[] args) {
		Stack<Number> numbers = new Stack<Number>();

		List<Integer> integers = Arrays.asList(1, 3, 5);
		List<Number> numbersList = Arrays.asList(2, 4, 6);

		System.out.println("Upper bounded - only sub types or the type itself");
		numbers.pushAll(integers);
		numbers.pushAll(numbersList);

		System.out.println("Lower bounded - only super types or the type itself");
		numbers.popAll(new ArrayList<Number>());
		numbers.popAll(new ArrayList<Object>());

		System.out.println("Internally using helper method to caputre the wildcard type");
		swap(Arrays.asList(1, 2), 0, 1);

	}

	public static void swap(List<?> list, int i, int j) {
		swapHelper(list, i, j);
	}

	private static <E> void swapHelper(List<E> list, int i, int j) {
		list.set(i, list.set(j, list.get(i)));
	}

}

class Stack<E> {
	java.util.Stack<E> internalStack = new java.util.Stack<>();

	public void push(E e) {
		internalStack.push(e);
	}

	public E pop() {
		return internalStack.pop();
	}

	public boolean isEmpty() {
		return internalStack.isEmpty();
	}

	public void pushAll(Iterable<? extends E> src) {
		for (E e : src)
			push(e); // push() produces E instances to use by the stack
	}

	public void popAll(Collection<? super E> dst) {
		while (!isEmpty())
			dst.add(pop()); // pop() consumes E instances from the stack
	}

}
