# Lang

Lang is a simple Java library that can handle multiple `.lang` files, and switch languages at runtime. The goal is to provide a minimal interface for multi-language programs.

## Installation

This library can be installed from [JitPack](https://jitpack.io/#ewpratten/lang).

## Usage

Lang expects to find `.lang` files in `resources/lang/`. These files follow the same format as [`.properties`](https://en.wikipedia.org/wiki/.properties) files. The following is an example `.lang` file at `resources/lang/en_us.lang`, and the Java code to interact with it:

```properties
test.greeting=Hello, world!
library.name=Lang
```

```java

// Set en_us as the primary language
LanguageManager.getInstance().setLanguage(new Language("en_us"));

// Other languages can also be set as a fallback, where their 
// definitions will be used in the event the main language is missing something
LanguageManager.getInstance().setFallbackLanguage(new Language("sv_se"));

// Run a query
assert LanguageManager.getInstance().query("test.greeting").equals("Hello, world!")

```
