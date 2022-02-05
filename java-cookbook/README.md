# Java Cookbook

### Table of contents
- [Setup example project](#setup-example-project)
- [Method References](method_references.md)	

## Video on Youtube
[![Part 1](https://img.youtube.com/vi/i2luxp4e714/maxresdefault.jpg)](https://youtu.be/i2luxp4e714)

## Setup example project
I use `Spring CLI` to create a `Spring-boot project`, here is the command:
```
spring init -a=org.wj.prajumsook -package=org.wj.prajumsook -d=web,lombok,h2,jpa java-cookbook
```
Project name `java-cookbook` will created with artifactId `org.wj.prajumsook` and dependencies `spring-boot-starter-web, lombok, h2` and `spring-boot-starter-data-jpa` will auto added to the project.

### Configure Datasource, JPA and H2
Just simple with `Spring-boot` only open file `src/main/resources/application.properties`, here are my properties:

```java
# Datasource
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# JPA
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update

# H2
spring.h2.console.enabled=true
spring.h2.console.path=/h2-ui
```
- `jdbc:h2:mem:testdb` is In-memory database
- `spring.jpa.show-sql=true` to show the hibernate-generated SQL into the console output.
- `spring.jpa.hibernate.ddl-auto=update` the values `create`, `create-drop`, `validate`, and `update` are the how schema tool management will manipulate the database schema at startup.
- and we enable H2 database console at for example at this url: `http://localhost:8080/h2-ui`


### Initialize data when startup
- create `currency.json` file.
- create currency model and entity class.
```java
	package org.wj.prajumsook.model;

	import javax.persistence.Column;
	import javax.persistence.Entity;
	import javax.persistence.GeneratedValue;
	import javax.persistence.GenerationType;
	import javax.persistence.Id;
	import javax.persistence.Table;

	import lombok.AllArgsConstructor;
	import lombok.Data;
	import lombok.NoArgsConstructor;

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Entity
	@Table
	public class Currency {

	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private Long id;

	  @Column(name = "name")
	  private String name;
		// other data fields are the same
		...
		...
	}	
```	

### Read JSON from resource
Here is the code:
```java
InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("Currency.json");
ObjectMapper mapper = new ObjectMapper();
JsonNode jsonNode = mapper.readValue(in, JsonNode.class);
List<Currency> currencies = mapper.readValue(jsonNode.toString(), new TypeReference<List<Currency>>(){});
```
And put it into the table:
```java
...
@Autowired
private CurrencyRepository repository;
...
currencies.forEach(c -> repository.save(c));
..
```

### Create API Resource
- create currency service

Here is the code:
```java
@Autowired
private CurrencyRepository repository;

Optional<List<Currency>> findAll() {
    try {
        return Optional.of(repository.findAll());
    } catch(DataAccessException de) {
        log.error("Error excecuting findAll", de);
        return Optional.empty();
    }
}
``` 
- create currency controller
```java
@RestController
@RequestMapping("/currency")
class CurrencyController() {
    @Autowired
private CurrencyService service;
...
...
...

}
```
- implement resource endpoints

Here is the code:
```java
@GetMapping("/all")
List<Currency> findAll() {
    return service.findAll();
}
```
