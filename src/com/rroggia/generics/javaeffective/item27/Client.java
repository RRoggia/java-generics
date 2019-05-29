package com.rroggia.generics.javaeffective.item27;

import java.util.Arrays;

public class Client {

	private Object[] elements;
	private int size;

	/** This method ignores the warning.
	 */
	public <T> T[] toArray(T[] a) {
		if (a.length < size)
			return (T[]) Arrays.copyOf(elements, size, a.getClass());

		System.arraycopy(elements, 0, a, 0, size);
		if (a.length > size)
			a[size] = null;

		return a;
	}

	/** This method uses the @SuppressWarning with the smallest scope and has a comment explaining why it is typesafe.
	 */
	public <T> T[] toArrayWithUnchecked(T[] a) {
		if (a.length < size) {
			// This cast is correct because the array we're creating is of the same type as
			// the one passed in, which is T[].
			@SuppressWarnings("unchecked")
			T[] result = (T[]) Arrays.copyOf(elements, size, a.getClass());
			return result;
		}

		System.arraycopy(elements, 0, a, 0, size);
		if (a.length > size)
			a[size] = null;

		return a;
	}

}
