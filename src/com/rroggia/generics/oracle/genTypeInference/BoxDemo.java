package com.rroggia.generics.oracle.genTypeInference;

import java.util.ArrayList;
import java.util.List;

import com.rroggia.generics.oracle.types.Box;

public class BoxDemo {

	public static <T> void addBox(T element, List<Box<T>> boxes) {
		Box<T> box = new Box<>();
		box.set(element);
		boxes.add(box);
	}

	public static <T> void outputBoxes(List<Box<T>> boxes) {
		int counter = 0;
		for (Box<T> box : boxes) {
			System.out.printf("Box #%d contains [%s]\n", counter, box.get().toString());
			counter++;
		}
	}

	public static void main(String[] args) {
		List<Box<Integer>> boxes = new ArrayList<>();

		addBox(10, boxes);
		addBox(20, boxes);
		addBox(30, boxes);

		outputBoxes(boxes);
	}

}
