# Java Effective

With generics, you tell the compiler inserts casts for you automatically and tells you at *compile time* if you try to insert an object of the wrong type.This results in programs that are safer and clearer.

### Definition of generic class and generic interface
Based on [JLS 8.1.2](https://docs.oracle.com/javase/specs/jls/se8/html/jls-8.html#jls-8.1.2).
Based on [JLS 9.1.2](https://docs.oracle.com/javase/specs/jls/se8/html/jls-9.html#jls-9.1.2).

A class is generic if it declares one or more type variables. These type variables are known as the type parameters of the class. The type parameter section follows the class name and is delimited by angle brackets. 

All of these parameterized types share the same class at run time.

``` java
Vector<String>  x = new Vector<String>();
Vector<Integer> y = new Vector<Integer>();
boolean b = x.getClass() == y.getClass(); // b contains true
```

### Item 26: Don't use raw types
Better to discover errors as soon as possible, ideally at compile time. Without using generics you'll discover the error at runtime. If you use raw types, you lose all the safety and expressiveness benefits of generics. 

Raw types should only be used for compatibility reasons, for example to integrate with legacy code.

Two exceptions to use Raw Types:
* As class literals. e.g. `List.class` because `List<String>.class` is not allowed.
* `instanceof` invocations: Since the generic type is erased at runtime, it's not allowed to cast `instanceof` on parameterized type, it's only allowed on the unbounded wildcarD. Therefore, adding the angle brackets and question mark is just noise.

```java
if (o instanceof Set) {
	Set<?> s = (Set<?>) o;
} 

