# java-generics

### What it is a generic type?
A Class or Interface is generic if it declares one or more *type variables*.[JLS, 8.1.2, 9.1.2]

### Why to use generics?
Generics enable types (classes and interfaces) to be parameters when defining classes, interfaces and methods.
* Ensures type safety during compile-time
* Eliminates casts of objects
* Enable to reuse code for different types

### How it works?
A generic type declaration is compiled once and for all, and turned into a single class file, just like an ordinary class or interface declaration. At this moment that happens the type erasure. Therefore, all instances of a generic class have the same run-time class, regardless of their actual type parameters. 

A *generic* declaration has *formal type parameters*. When a generic declaration is invoked, the *actual type arguments* are substituted for the *formal type parameters*.

#### Generics with Casts, InstanceOf and Arrays
Type variables don't exist at run time. This means that they entail no performance overhead in either time nor space, which is nice. Unfortunately, it also means that you can't reliably use them in casts.

he component type of an array object may not be a type variable or a parameterized type, unless it is an (unbounded) wildcard type.You can declare array types whose element type is a type variable or a parameterized type, but not array objects. 

See the fineprint `package` for the examples.


### Type parameter Naming Conventions
By convention, type parameter names are single, uppercase letters.

* E - Element (used extensively by the Java Collections Framework)
* K - Key
* N - Number
* T - Type
* V - Value
* S,U,V etc. - 2nd, 3rd, 4th types

### Generic Method
The compiler infers the type argument for us, based on the types of the actual arguments. It will generally infer the most specific type argument that will make the call type-correct. `com.rroggia.generics.oracle.genTypeInference` demonstrate the type inference.

Generic methods allow type parameters to be used to express dependencies among the types of one or more arguments to a method and/or its return type. If there isn't such a dependency, a generic method should not be used.

### Bounded type parameter
Requires the *type parameter* to `extends|implements` the `Comparable` and it allows you to invoke methods defined in the `Comparable`. 

```` java
public static <T extends Comparable<T>> int countGreaterThan(T[] anArray, T elem) {
    int count = 0;
    for (T e : anArray)
        if (e.compareTo(elem) > 0) \\compareTo is a method defined in the bound
            ++count;
    return count;
}
````

### Inheritance
Given two concrete types `A` and `B` (for example, `Number` and `Integer`), `MyClass<A>` has no relationship to `MyClass<B>`, regardless of whether or not `A` and `B` are related. The common parent of `MyClass<A>` and `MyClass<B>` is `Object`.

You can subtype a generic class or interface by extending or implementing it. Using the `Collections` classes as an example, `ArrayList<E>` implements `List<E>`, and `List<E>` extends `Collection<E>`. So `ArrayList<String>` is a subtype of `List<String>`, which is a subtype of `Collection<String>`. 

### Type Inference
Determination of the type argument that make the invocation applicable. The inference algorithm tries to find the **most specific** type that works with all of the arguments.

Type inference helps to reduce the verbosity of the code, making it easier to read. Type inference makes unnecessary to specify the type in the generic method call and during the instantiation of a class. 

### Raw Type 
Raw types are very much like wildcard types, but they are not typechecked as stringently. It was a design decision to enable generics code with pre-existing legacy code. therefore it should be avoided.

### Wildcards
Represents an unknown type. Mainly used as the type of a parameter, field, or local variable. Wildcards also have the advantage that they can be used outside of method signatures, as the types of fields, local variables and arrays.

### Upper Bounded Wildcards
You can use an upper bounded wildcard to relax the restrictions on a variable. It matches the type and any of its subtypes. 

### Unbounded Wildcards
A method that can be implemented using functionality provided in the `Object` class or a generic class that don't depend on the type parameter. In fact, `Class<?>` is so often used because most of the methods in `Class<T>` do not depend on `T`.

This is consider the supertype of all kinds of objects. For example, the `Collection<?>` can be considered a *collection of unknown*, that is, a collection whose elements types matches anything.

### Lower Bound Wildcards
Restricts the unknown type to be a specific type or a super type of that type.

### Guidelines for wildcard use
**An "In" Variable**

An "in" variable serves up data to the code. Imagine a copy method with two arguments: copy(src, dest). The src argument provides the data to be copied, so it is the "in" parameter.

**An "Out" Variable**

An "out" variable holds data for use elsewhere. In the copy example, copy(src, dest), the dest argument accepts data, so it is the "out" parameter.

**Wildcard Guidelines:** 
* An "in" variable is defined with an upper bounded wildcard, using the extends keyword.
* An "out" variable is defined with a lower bounded wildcard, using the super keyword.
* In the case where the "in" variable can be accessed using methods defined in the Object class, use an unbounded wildcard.
* In the case where the code needs to access the variable as both an "in" and an "out" variable, do not use a wildcard.

**These guidelines do not apply to a method's return type. Using a wildcard as a return type should be avoided because it forces programmers using the code to deal with wildcards.**

The return type doesn't depend on the type parameter, nor does any other argument to the method. This tells us that the type argument is being used for polymorphism; its only effect is to allow a variety of actual argument types to be used at different invocation sites. If that is the case, one should use wildcards. Wildcards are designed to support flexible subtyping, which is what we're trying to express here.

Using wildcards is clearer and more concise than declaring explicit type parameters, and should therefore be preferred whenever possible.

### Wildcards and Subtyping
Given the inheritance in the generic class (E.g. `ArrayList<E> implements List<E>`). The following statements are valid:
* `List<Number>` has a relationship with `List<? super Number>`. Since `Number` is the specific type of the lower bound wildcard.
* `List<Number>` has a relationship with `List<? super Integer>`. Since `Number`is a supertype of `Integer`.
* `List<Number>` has a relationship with `List<?>`. Since `Number` is a concrete type and `?` is a supertype for all the concrete types. Same applies for `List<? super Number>` and `List<? super Integer>`.
* `List<Number>` has a relationship with `List<? extends Number>`. Since `Number` is the specific type of the upper bound wildcard.  
* `List<? super Number>` has a relationshop with `List<? super Integer>`. Since `Number` is a supertype of `Integer`.
* `List<Integer>` has a relationship with `List<? super Integer>`. Since `Integer` is the specific type of the lower bound wildcard.
* `List<Integer>` has a relationship with `List<? extends Integer>`. Since `Integer` is the specific type of the upper bound wildcard.
* `List<Integer>` has a relationship with `List<? extends Number>` Since `Integer` is a subtype of `Number`.
* `List<? extends Integer>` has a relationship with `List<? extends Number>`. Since `Integer` is a subtype of `Number`.
* `List<Integer>` has a relationship with `List<?>`. Since `Integer` is a concrete type and all concrete types are subtypes of `?`.

### Type Erasure
* Replace all type parameters in generic types with their bounds or Object if the type parameters are unbounded. The produced bytecode, therefore, contains only ordinary classes, interfaces, and methods.
* Insert type casts if necessary to preserve type safety.
* Generate bridge methods to preserve polymorphism in extended generic types.

### Type Parameter vs Bounded Type Parameter vs upper bounded wildcards vs unbounded wildcards
*Type parameter* can only assume one type argument at time.

*Bounded Type Parameter* can assume one type argument and its subtypes. Enable usage of specific methods of a type.

*Unbounded wilcards* can assume several type argument at time.

*Upper Bounded Wildcards* can assume several type argument at time and its subtypes. Enable usage of specific methods of a type.

*Lower Bounded Wildcards* can assume several type argument at time and its supertypes.

### Glosary
* *type variable* | *type parameter* | *formal type parameter* : An unqualified identifier used as a type in class, interface, method, and constructor bodies. A type variable can be any **non-primitive** type you specify: Any class type, any interface type, any array type, or even another type variable. Examples: `E` or `T`.
* *generic type* : A generic class or interface that is parameterized over types. Example: `List<E>`.
* *generic type invocation* : Replacement of a *type parameter* `T` to a *type argument*, such as `Integer`. Example: `new Box<Integer>();`.
* *type argument* | *actual type parameter*:  The *actual type parameter* of a *formal type parameter*. Example: `Integer`. 
* *parameterized type* : Class or interface followed by an angle-bracketed list of the *actual type parameter*. Example:`List<Integer>`.
* *raw type* : *Generic type* without any type arguments. Example `List`.
* *generic methods* : Methods that introduce their own *type parameters*. Example: `static <E> List<E> asList(E[] a)`.
* *bounded type parameters* : To restrict the types that can be used as *type argument*. In this context, the `extends` represents both `extends` and `implements`. Example: `<E extends Number>` or `<E extends Number & InterfaceA & InterfaceB>`. 
* *type witness* : During the generic method invocation, you can avoid the *type inference* by specifying *type argument*. Example: `List.<Integer>add(10)`.
* *Upper bounded wildcards* : To relax the restrictions on a variable. In this context, the `extends` represents both `extends` and `implements`. Example: `<? extends Number>`.
* *unbounded wildcards* : Unknown type. To be used when the type is not relevant or can be implemented with `Object` methods. Example: `?`
* *lower bound wildcards* : To relax the restrictions on a variable. Example: `<? super Number>`.

## References
### Online
* [Oracle tutorial](https://docs.oracle.com/javase/tutorial/java/generics/index.html)
* [Gilad Bracha tutorial](https://docs.oracle.com/javase/tutorial/extra/generics/index.html)

### Books
* learningjava -> chapter 8.
* javapocketguide -> chapter 16.
* javainanutshell -> chapter 4 -> Java Generics
* javagenericsandcollections -> chapters 1~9 
* javacookbook -> pages 199~203
* javaeffective -> chapter5
