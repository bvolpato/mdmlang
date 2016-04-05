mdmlang
========

[![Apache License](http://img.shields.io/badge/license-ASL-blue.svg)](https://github.com/brunocvcunha/mdmlang/blob/master/LICENSE)
[![Build Status](https://travis-ci.org/brunocvcunha/mdmlang.svg)](https://travis-ci.org/brunocvcunha/mdmlang)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.brunocvcunha.mdmlang/mdmlang/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.brunocvcunha.mdmlang/mdmlang)
[![Coverage Status](https://coveralls.io/repos/github/brunocvcunha/mdmlang/badge.svg?branch=master)](https://coveralls.io/github/brunocvcunha/mdmlang?branch=master)

Data transformation language that makes the process of mapping data easier and with a natural syntax.
The grammar for this DSL is based on [Antlr4](http://www.antlr.org/).

Available Operations
--------
There is some operations that you are able to use (some variations allowed, please check the [grammar file](https://github.com/brunocvcunha/mdmlang/blob/master/src/main/antlr4/org/brunocvcunha/mdmlang/antlr/MDMRule.g4)):

- keep only numbers of `field`
- trim `field`
- remove leading zeros from `field`
- remove 3 chars from left of `field`
- substring 2 to 3 of `field`
- truncate at 20 `field`
- return `field`


Usage Example
--------

source-data.json
```json
{
    "it-codigo": " my item ",
    "desc-item": " large description that contains more than 20 chars ",
    "num-ddd": 47,
    "num-telefone": "number 9000-1000"
}
```

mdmlang-rules.txt
```text
itemCode:
    trim it-codigo
    return it-codigo

description:
    trim desc-item
    truncate at 20 desc-item
    return desc-item

phoneNumber:
    trim num-telefone
    keep only numbers of num-telefone
    return num-ddd and num-telefone
```

Java App:
```java
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.brunocvcunha.mdmlang.processor.MDMProcessorContext;

public class MDMLanguageMain {
    public static void main(String[] args) throws IOException {
        Gson gson = new Gson();

        InputStream rules = MDMLanguageMain.class.getResourceAsStream("/mdmlang-rules.txt");
        InputStream json = MDMLanguageMain.class.getResourceAsStream("/source-data.json");

        MDMProcessorContext ctx = MDMProcessorContext.buildContext(rules);
        Type type = new TypeToken<Map<String, Object>>() {}.getType();

        Map<String, Object> values = gson.fromJson(new InputStreamReader(json), type);
        Map<String, Object> golden = ctx.process(values);

        System.out.println(gson.toJson(golden));
    }
}
```


Outputs Transformed Data:
```json
{
    "itemCode": "my item",
    "description": "large description th",
    "phoneNumber": "4790001000"
}
```



Download
--------
(Release still on process)

Download [the latest JAR][1] or grab via Maven:
```xml
<dependency>
  <groupId>org.brunocvcunha.mdmlang</groupId>
  <artifactId>mdmlang</artifactId>
  <version>1.0</version>
</dependency>
```
or Gradle:
```groovy
compile 'org.brunocvcunha.mdmlang:mdmlang:1.0'
```

Snapshots of the development version are available in [Sonatype's `snapshots` repository][snap].

MDM Lang requires at minimum Java 7.


 [1]: https://search.maven.org/remote_content?g=org.brunocvcunha.mdmlang&a=mdmlang&v=LATEST
 [snap]: https://oss.sonatype.org/content/repositories/snapshots/
