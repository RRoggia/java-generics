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

### Item 29: Favor Generic Types
It is generally not too difficult to parameterize your declaration and make use of the generic types and methods provided by the JDK. Writing your own generic type is a bit more difficult.

The `Stack` class is implemented without the generics. It uses an `Object[]` to store the elements, the `push(Object e)` enables to pushes whatever subtype of Object to the stack, because of that the `pop` relies on a cast to the specific subtype which may lead to `ClassCastException`. 

The `Stack1` class implementation relies on generics. It uses an `E[]` to store the elements. Since you cannot create `new E[]`, because, `E` is a *non refiable* type, during the constructor we create a `Object[]` and cast it to the type `E[]`.

The `Stack2` class implementation relies on generics. It uses an `Object[]` to store the elements. The `push(E e)` method ensures the array will only contain objects of the type `E`, therefore, when `pop()` method is called is safe to cast the array element to the type `E`. The down side of this approach is that you can end up with several casts to the type `E`.

### Item 30: Favor Generic Methods
Static utility methods that operate on parameterized types are usually generic. For example, `binarySearch` and `sort` from `Collections`.

To create objects that are immutable but applicable to many different types, you can apply the *generic singleton factory* pattern by taking advantage of the erasure of the generic. The `identityFunction` method exemplifies the pattern.

We can also have a type parameter that is bound to a expression involving that type parameter itself, it's called *recursive type bound*. For example: `<E extends Comparable<E>>`. It may be read as "any type `E` that can be compared to itself".

### Item 31: Use bounded wildcards to increase API flexibility
As noted in Item 28, parameterized types are *invariant*. However, is possible to achieve the behavior using *bounded wildcards*.
* Lower bounded wildcard: will accept any supertype or the type itself
* Upper bounded wildcard: will accept any subtype or the type itself.

For maximum flexibility, use wildcard types on input parameters that represent producers or consumers. If the input parameter is both producer and consumer use an exact type match.

PECS stands for producer-extends, consumer-super.

-->> **SEE THE GET AND PUT PRINCIPLE FROM JAVA-GENERICS AND COLLECTIONS** <<--

Examples:
* Item28: `Chooser4` Class
* Item30: `unionBoundedWildCardType` method
* Item30: `max` method

Do not use *bound wildcard types* as return types. They require the client code to work with wildcard. Properly used, wildcard types are nearly invisible to the users of a class. If the user of a class has to think about wildcard types, there is probably something wrong with its API.

`Comparable<T>` and `Comparator<T>` are always consumers, therefore, preferably use `<? super T>`.

As a rule, if a type parameter appears only once in a method declaration, replace it with a wildcard. If it's a unbounded type parameter, replace it with an unbounded wildcard. If it's a bounded type parameter, replace it with a bounded wildcard.

### Item 32: Combine generics and varargs judiciously
Varargs methods and generics were both added to the platform in Java 5, so you might expect them to interact gracefully; sadly, they do not.

If a method declares its varargs parameter to be of a non-reifiable type `T...` the warning compiler generates a warning on the declaration.

 `warning: [unchecked] Possible heap pollution from parameterized vararg type T`
 
The methods `getParameters` and `dangerous` in the class `com.rroggia.generics.javaeffective.item32.Client` demonstrates the warning message.  

Heap pollution occurs when a variable of a parameterized type refers to an object that is not that type [JLS 4.12.2] It can cause the compiler's automatically generated casts to fail, violating the fundamental guarantee of the generic type system.

It's unsafe to store a value in a generic varargs array parameter.

There are typesafe methods with varargs parameters of generic or parameterized types in the java library. `Arrays.asList(T... a)`,`Colections.addAll(Collection<? super T> c, T... elements)`.

In Java 7, the `SafeVarargs` annotation was added to the platform, to allow the author of a method with generic varargs parameter to suppress client warnings automatically. In essence, the SafeVarargs constitutes a promise by the author of a method that it is typesafe.

It is critical that you do not annotate a method with `@SafeVarargs` unless it is actually safe.

**Ensuring a method with varargs parameters of generic or parameterized types is safe:** 
If the method doesn't store anything into the array (which would overwrite the parameters) and doesn't allow a reference to the array to escape (which would enable untrusted code to access the array). Then it's safe.   

In other words, if the varargs parameter array is used only to transmit a variable number of arguments from the caller to the method then the method is safe.

The method `toArray` in the class `com.rroggia.generics.javaeffective.item32.Client` demonstrates how is dangerous to expose the reference of a generic's vararg parameter.

It's unsafe to give another method access to a generic varargs parameter array.

The method `flatten` in the class `com.rroggia.generics.javaeffective.item32.Client` demonstrates how a safe implementation of the combination looks like. Also notice the usage of the `@SafeVarargs` annotation.

As a rule of thumb, use `@SafeVarargs` on every method with a varargs parameter of a generic type or parameterized type, so its user won't be burdened by needless and confusing compiler warnings. This implies that you should never write unsafe varargs methods like `dangerous` or `toArray`.

An alternative to using the `SafeVarargs` annoattation is to take the advice of Item28 and replace the varargs parameter (which is an array in disguise) with a `List` parameter.




