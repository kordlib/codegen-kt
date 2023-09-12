# Module structure

<tldr id="module_tldr">
    <p>
        Artifact: <shortcut>%artifactName%</shortcut>
    </p>
    <p>
        Needs annotation processor: %annotationProcessor%
    </p>
</tldr>

<snippet id="installation">
<tabs>
<tab title="Gradle (Kotlin DSL)">

```kotlin
repositories {
    mavenCentral()
    // Kord Snapshots Repository (Optional):
    maven("https://oss.sonatype.org/content/repositories/snapshots")
}

dependencies {
    implementation("dev.kord.codegen:%artifactName%:%version%")
}
```
</tab>

<tab title="Gradle (Groovy DSL)">

```groovy
repositories {
    mavenCentral()
    // Kord Snapshots Repository (Optional):
    maven {
        url "https://oss.sonatype.org/content/repositories/snapshots"
    }
}
dependencies {
    implementation("dev.kord.codegen:%artifactName%:%version%")
}
```

</tab>

<tab title="Maven">

```xml

<dependency>
    <groupId>dev.kord.codegen</groupId>
    <artifactId>%artifactName%</artifactId>
    <version>%version%</version>
</dependency>
```
<chapter title="Kord Snapshot Repository (Optional)" collapsible="true" id="maven-snapshot-repository">

```xml
<repository>
    <id>snapshots-repo</id>
    <url>https://oss.sonatype.org/content/repositories/snapshots</url>
    <releases>
        <enabled>false</enabled>
    </releases>
    <snapshots>
        <enabled>true</enabled>
    </snapshots>
</repository>
```
</chapter>
</tab>
</tabs>
</snippet>
