# jbibx

A Java library for reading and writing `.bibx` files.

Compatible with Java 7+.


## What's it good for?

`.bibx` is rBiblia's custom format for storing Bible translations. It's based on open standards (XML and gzip) and very simple.

This library brings `.bibx` into the Java world. It completely automates serialization and deserialization of `.bibx` files.


## Usage

First, add it to your project. For Gradle, add a repository and dependency to `build.gradle`:

```groovy
repositories {
    maven { url 'https://jitpack.io' }
}
dependencies {
    compile 'com.github.rBiblia:jbibx:master-SNAPSHOT'
}
```

For other build systems, see [instructions on JitPack.io](https://jitpack.io/).

Then, to load a `.bibx` file:

```java
Bible bible = new BibxSerde().deserialize(new File("SomeBible.bibx"));
```

To write a `bible` to a file:

```java
new BibxSerde.serialize(bible, new File("SomeBible.bibx"))
```

`Bible` class and its relatives are immutable, so if you want to edit existing translations, you'll have to allocate new ones.
