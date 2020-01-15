package com.rroggia.generics.javaeffective.item33;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.HashMap;
import java.util.Map;

public class Client {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		Favorite favorite = new Favorite();

		favorite.putFavorite(String.class, "Renan");
		favorite.putFavorite(Integer.class, 10);
		favorite.putFavorite(Class.class, Favorite.class);

		String string = favorite.getFavorite(String.class);
		Integer integer = favorite.getFavorite(Integer.class);
		Class<?> clazz = favorite.getFavorite(Class.class);

		System.out.printf("%s %d %s%n", string, integer, clazz.getName());

		System.out.println("Example of corrupting typesafete using raw types");
		HashMap a = new HashMap();
		a.put("Roggia", 10);
		a.put(10, "roggia");

		// getAnnotation(AnnotatedClass.class,
		// "com.rroggia.generics.javaeffective.item33.AnnotatedClass");
	}

	// example of bounded type token -> heavily used in the annotations API
	public <T extends Annotation> T getAnnotation(Class<T> annotation) {
		return null;
	}

	static Annotation getAnnotation(AnnotatedElement element, String annotationTypeName) {
		Class<?> annotationType = null; // unbounded type token
		try {
			annotationType = Class.forName(annotationTypeName);
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
		return element.getAnnotation(annotationType.asSubclass(Annotation.class));
	}

}

class Favorite {
	private Map<Class<?>, Object> favorites = new HashMap<>();

	public <T> void putFavorite(Class<T> clazz, T instance) {
		favorites.put(clazz, clazz.cast(instance));
	}

	public <T> T getFavorite(Class<T> clazz) {
		return clazz.cast(favorites.get(clazz));
	}
}
