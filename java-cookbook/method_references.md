# Method References

You can use method reference to access an existing method like a lambda expression.

How to use?
```
Use the double-colon notation to separate an instance references or class name from the method.
```
### Syntax
- Instance method reference
```java
object::instanceMethod

// exp.
System.out::println;
// equal to
x -> System.out.print(x);

```
- Static method reference
```java
Class::staticMethod

// exp.
Math::max
// equal to
(x, y) -> Math.max(x, y);
```
- Instance method through the class name
```java
Class::instanceMethod

// exp.
String::length
// equal to
x -> x.length();
```


	 