# java-generics

### What it is a generic type?
A Class or Interface is generic if it declares one or more *type variables*.[JLS, 8.1.2, 9.1.2]

### Why to use generics?
Generics enable types (classes and interfaces) to be parameters when defining classes, interfaces and methods.
* Ensures type safety during compile-time
* Eliminates casts of objects
* Enable to reuse code for different types

### Type parameter Naming Conventions
By convention, type parameter names are single, uppercase letters.

* E - Element (used extensively by the Java Collections Framework)
* K - Key
* N - Number
* T - Type
* V - Value
* S,U,V etc. - 2nd, 3rd, 4th types

### Glosary
* *type variable* | *type parameter* | *formal type parameter* : An unqualified identifier used as a type in class, interface, method, and constructor bodies. A type variable can be any **non-primitive** type you specify: any class type, any interface type, any array type, or even another type variable. Examples: `E` or `T`.
* *generic type* : A generic class or interface that is parameterized over types. Example: `List<E>`.
* *generic type invocation* : Replacement of a *type parameter* `T` to a *type argument*, such as `Integer`. Example: `new Box<Integer>();`.
* *type argument* | *actual type parameter*:  The *actual type parameter* of a *formal type parameter*. Example: `Integer`. 
* *parameterized type* : Class or interface followed by an angle-bracketed list of the *actual type parameter*. Example:`List<Integer>`
* *raw type*: *generic type* without any type arguments. Example `List`

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
