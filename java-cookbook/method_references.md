# Method References
- [Syntax](#syntax)
- [Advantage](#advantages-over-the-lambda-syntax)
- [Real world example](#real-world-example)
- [Converting](#converting)
- [Converting to DTO](#converting-to-dto)

You can use method reference to access an existing method like a lambda expression.

## Video on Youtube
[![Part 1](https://img.youtube.com/vi/nqR2N-Mdhdw/maxresdefault.jpg)](https://youtu.be/nqR2N-Mdhdw)

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
### Advantages over the lambda syntax
- Shorter
- Often includes the name of the class containing the method
- Easier to read

### Real world example
From our test project we have this syntax to save JSON data to database:
```java
...
currencies.forEach(c -> repository.save(c));
...
```
We can rewrite this to Method reference:
```java
...
Consumer<Currency> consumer = repository::save;
currencies.forEach(consumer);
```
Output:
![img.method-ref-03]
### Converting
We want to convert our currency list of `Currency` object to a list of currency `code` for example:
```java
...
List<Currency> currencies = repository.findAll();

// using Lambda expression
List<String> codes = currencies.stream().map(c -> c.getCode()).collect(Collectors.toList());

// using Method reference
List<String> codes = currencies.stream().map(Currency::getCode).collect(Collectors.toList());

```
Output:
![img.method-ref-02]
### Converting to DTO
We want to convert the currency entity class to currency DTO:
- create a currency DTO class 
```java
@Data
@NoArgsConstructor
public class CurrencyDTO {
    private String name;
    private String code;
    private String symbol;

    public CurrencyDTO(Currency currency) {
        this.name = currency.getName();
        this.code = currency.getCode();
        this.symbol = currency.getSymbol();
    }
}
```
- Use Constructor reference to convert entity to DTO
```java
List<Currency> currencies = repository.findAll();
List<CurrencyDTO> dtos = currencies.stream()
    .map(CurrencyDTO::new)
    .collect(Collectors.toList());
```
output:
![img.method-ref-01]

[img.method-ref-01]: img/method-ref-01.png
[img.method-ref-02]: img/method-ref-02.png
[img.method-ref-03]: img/method-ref-03.png	 
