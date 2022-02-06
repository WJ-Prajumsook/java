# Processing File with Streams

- [The try-with-resources Statement](#the-try-with-resources-statement)
- [Read CSV file and put it into Java object](#read-csv-file-and-put-it-into-java-object)

In this tutorial we will explore Streams with file processing.

In this example I will read CSV file and proccessing the content of the file.

My CSV file content looks like this:
![img.stream-01]

Read CSV file from `resources`:
```java
try {
    InputStream in  = Thread.currentThread().getContextClassLoader().getResourceAsStream("BTC-USD.csv");
    Stream<String> lines = new BufferedReader(new InputStreamReader(in)).lines();

    lines.forEach(System.out::println);
} catch(Exception ex) {
    ex.printStackTrace();
}
```
- Read CSV file from resources
- Use lines() method on BufferedReader to convert to Streams of lines.

### The try-with-resources Statement

The try-with-resources statement is a try statement that declares one or more resources. A resource is an object that must be closed after the program is finished with it. The try-with-resources statement ensures that each resource is closed at the end of the statement. Any object that implements java.lang.AutoCloseable, which includes all objects which implement java.io.Closeable, can be used as a resource.

We can also use `try-with-resources` block.
```java
try (
    InputStream in  = Thread.currentThread().getContextClassLoader().getResourceAsStream("BTC-USD.csv");
    Stream<String> lines = new BufferedReader(new InputStreamReader(in)).lines();
) {
    lines.forEach(System.out::println);
}
```
Because Streams are implementing AutoCloseable so it will now be automatically closed when the try block is exited.

### Read CSV file and put it into Java object

First create a POJO class:
```java
@Data
@NoArgsContructor
@AllArgsContructor
@Accessors(chain = true)
public class CryptoHistory {

    private String date;
    private String open;
    private String high;
    private String low;
    private String close;
    private String adjClose;
    private String volumn;

}
```
Read CSV data from file:
```java
public List<CryptoHistory> readCSV() throws IOException {
    Pattern pattern = Pattern.compile(",");
    try (
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("BTC-USD.csv");
        Stream<String> lines = new BufferedReader(new InputStreamReader(in)).lines();
    ) {
        List<CryptoHistory> crypts = lines.skip(1).map(line -> {
            String[] strArr = pattern.split(line);
            return new CryptoHostory()
                .setDate(arr[0])
                .setOpen(arr[1])
                .setHigh(arr[2])
                .setLow(arr[3])
                .setClose(arr[4])
                .setAdjClose(arr[5])
                .setVolume(arr[6]);
        }).collect(Collectors.toList());
        
        return strArr; 
    }
}
``` 

[img.stream-01]: img/streams-01.png
