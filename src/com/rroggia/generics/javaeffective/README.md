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
```

### Item 27: Eliminate unchecked warnings
If you eliminate all warnings, you are assured that your code is typesafe, which means you won't get a `ClassCastException` at runtime.

If you can't eliminate a warning, but you can prove that the code that provoked the warning is typesafe, then (and only then) suppress the warning with an `@SuppressWarnings("unchecked")` annotation.
If you suppress warnings without first proving that the code is typesafe, you are giving yourself a false sense of security. If you ignore unchecked warnings that you know to be safe (instead of suppressing them), you won't notice when a new warnings crops up that represents a real problem.

Always use the `SuprressWarning` on the smallest scope possible.

Every time you use a `@SuppressWarnings("unchecked")` annotation, add a comment saying why it is safe to do so.

### Item 28: Prefers lists to arrays
Two important differences between lists and arrays:
1. Arrays are *covariant* and Lists are *invariant*.

```java
Object[] objectArray = new Long[1]; //valid due to covariant

List<Object> ol = new ArrayList<Long>(); //invalid, types are incompatible
```

With lists you can spot an error at compile time, with arrays you can only notice it at runtime.
 
2. Arrays are *reified* and Generics are implemented with *erasure* and therefore are *non reifiable* types.
Arrays knows and enforces their element type at runtime.

Generics by contrast erases their element type at runtime, which means generics only enforces their type constraint at compile time. So, a list has less information about its type parameter at runtime than at compiler time.

Because of the second characteristic of the generics, it's not a good idea to mix generics and arrays. Notice, it's also valid for vargars methods, since they use arrays to hold the varargs parameters.
