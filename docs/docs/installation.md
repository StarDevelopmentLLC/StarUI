---
hide:
  - navigation
---

# Installation
StarUI runs as a plugin and is only intended to be run as a plugin. StarUI has no dependencies other than Spigot.  
However, there are two ways to use this library, as a plugin and as a shaded library.  
Dependency information is nearly the same, except you have to change some slight things.

## Choosing a Build Tool
The two primary build tools for Java are Maven and Gradle. Pick one and stick with it.

## JitPack Repository
Star Development LLC uses JitPack as the repository for development. You must have this repository in whichever build tool you wish to use.
### Maven
```xml 
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

### Gradle
```groovy
repositories {
    maven { url 'https://jitpack.io' }
}
```

## StarUI Dependency Information
**Replace `{VERSION}` with the version of StarUI you are using!**
### Maven
```xml
<dependency>
    <groupId>com.github.StarDevelopmentLLC</groupId>
    <artifactId>StarUI</artifactId>
    <version>{VERSION}</version>
    <scope>provided</scope>
</dependency>
```
If you are using StarUI as a shaded library, make sure you add the `maven shade plugin` and change the scope to `compile` or just remove the scope option. 
### Gradle
```groovy
dependencies {
    compileOnly 'com.github.StarDevelopmentLLC:StarUI:{VERSION}'
}
```
If you are using StarUI as a shaded library, make sure you add the `shadow` Gradle plugin and change `compileOnly` to `implementation`

After you are done installing StarUI, take a look at [GuiManager](guimanager.md) to see the entry point into the library. 