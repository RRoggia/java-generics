package com.rroggia.generics.javaeffective.item29;

import java.util.Arrays;
import java.util.EmptyStackException;

public class Client {

	public static void main(String[] args) {
		System.out.println("Stack not using genercis");
		System.out.println("* push() allows adding whatever subtype of Object");
		System.out.println("* pop() relies on the cast to the specific subtype");
		System.out.println("May have runtime error ClassCastException");
		Stack stack = new Stack();
		stack.push(1);
		stack.push("lala");
		String pop = (String) stack.pop();

		System.out.println();

		System.out.println("Stack1 using genercis (elements stored E[])");
		System.out.println("* push() allows only adding E");
		System.out.println("* pop() returns E and client don't have to cast");
		System.out.println("Won't have runtime error ClassCastException");
		Stack1<String> stack1 = new Stack1<>();
		stack1.push("lala");
		String pop1 = stack1.pop();
		
		System.out.println();

		System.out.println("Stack2 using genercis (elements stored E[])");
		System.out.println("* push() allows only adding E");
		System.out.println("* pop() returns E and client don't have to  cast");
		System.out.println("Won't have runtime error ClassCastException");
		Stack2<String> stack2 = new Stack2<>();
		stack2.push("lala");
		String pop2 = stack2.pop();

	}

}

class Stack {
	private Object[] elements;
	private int size = 0;
	private static final int DEFAULT_INTITIAL_CAPACITY = 16;

	public Stack() {
		elements = new Object[DEFAULT_INTITIAL_CAPACITY];
	}

	public void push(Object e) {
		ensureCapacity();
		elements[size++] = e;
	}

	public Object pop() {
		if (size == 0)
			throw new EmptyStackException();

		Object result = elements[--size];
		elements[size] = null;
		return result;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	private void ensureCapacity() {
		if (elements.length == size)
			elements = Arrays.copyOf(elements, 2 * size + 1);
	}
}

class Stack1<E> {
	private E[] elements;
	private int size = 0;
	private static final int DEFAULT_INITIAL_CAPACITY = 16;

	@SuppressWarnings("unchecked")
	public Stack1() {
		// The element array will contain only E instances from push(E).
		// This is sufficient to ensure type safety, but the runtime type of the array
		// won't be E[]; it will always be Object[]!
		elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
	}

	public void push(E e) {
		ensureCapacity();
		elements[size++] = e;
	}

	public E pop() {
		if (size == 0)
			throw new EmptyStackException();

		E result = elements[--size];
		elements[size] = null;
		return result;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	private void ensureCapacity() {
		if (elements.length == size)
			elements = Arrays.copyOf(elements, 2 * size + 1);
	}
}

class Stack2<E> {
	private Object[] elements;
	private int size = 0;
	private static final int DEFAULT_INITIAL_CAPACITY = 16;

	public Stack2() {
		elements = new Object[DEFAULT_INITIAL_CAPACITY];
	}

	public void push(E e) {
		ensureCapacity();
		elements[size++] = e;
	}

	public E pop() {
		if (size == 0)
			throw new EmptyStackException();

		// push requires elements to be of type E, so cast is correct
		@SuppressWarnings("unchecked")
		E result = (E) elements[--size];
		elements[size] = null;
		return result;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	private void ensureCapacity() {
		if (elements.length == size)
			elements = Arrays.copyOf(elements, 2 * size + 1);
	}
}
