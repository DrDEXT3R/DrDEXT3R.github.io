---
title: "Jar2Exe #1: Creating Custom Runtime Image"
date: 2019-08-31
categories:
    - Java
tags:
    - JavaFX
    - Jar2Exe
header:
    image: "/assets/images/blog/creating-custom-runtime-image/custom-jre.png"
    teaser: "/assets/images/blog/creating-custom-runtime-image/custom-jre-teaser.png"
    og_image: "/assets/images/blog/creating-custom-runtime-image/custom-jre-teaser.png"
excerpt: "Java 9 introduced a new feature in programming in this language, which is the ability to create modules. By module, we mean a set of packages together with a file describing its content (descriptor). The main purpose of the Java Platform Module System (JPMS) is to reduce the size of the application."
---

Java 9 introduced a new feature in programming in this language, which is the ability to create modules. **By module, we mean a set of packages together with a file describing its content (descriptor)**.

> <div style="text-align: justify">A module is essentially a set of packages that make sense being grouped together and is designed for reuse.</div>
> <div style="text-align: right">blogs.oracle.com/java/modular-development</div>

## Java Platform Module System
The main purpose of the Java Platform Module System (JPMS) is to **reduce the size of the application**. Thanks to that, we can run virtual machines on devices with significantly smaller resources, which gives us an **increase in performance**. But how was this achieved? The answer is: from Java 9 you can isolate and compile application modules along with determining the dependencies required and provided by the module. This replaces the need to attach all JARs to each application separately. Instead, **we build Java runtimes that is tailored to the specific application (only required items are included)**.

It is worth noting that **modules are not a mandatory element of programs** written in newer versions of JDK, but only an additional option. An example of the program on which this tutorial will be based is my *CLP Calculator* (download the <a href="https://sourceforge.net/projects/clp-calculator/files/CLP-Calculator.jar/download" target="_blank">jar file</a>). It's built on JDK 11 and OpenJFX. I remind you that **from version 11 the JavaFX platform for GUI creation is no longer an integral part of JDK and must be downloaded separately**. If you have any problems, check my previous <a href="/java/your-javafx-app-on-jdk-11-&-openjfx/" target="_blank">post</a> in which I show how to run JavaFX application on JDK 11 & OpenJFX. 

**The tool that will allow us to generate a runtime image is called *"jlink"*** (available from JDK 9). It should be noted that jlink works with modules. What about applications that migrate, e.g. from JDK 8 to JDK 11? Fortunately, all the application code along with all external libraries can be treated as part of the module with no name.

## Custom Runtime Image on Windows x86
As you can see, both the new JDK versions and OpenJFX are only available for Windows 64-bit. Generated a runtime image will only support this version of systems. So what about the 32-bit ones?

First you need to **look for JDK that will work on these versions of the systems**. Since Java 9, there are no official sources that are compatible with 32-bit architecture. Fortunately, you can find other companies that share this: <a href="https://www.azul.com/downloads/zulu-community/" target="_blank">*Zulu OpenJDK*</a> or <a href="https://bell-sw.com/" target="_blank">*Liberica JDK*</a>.

A similar situation applies to OpenJFX. There are no official sources for such systems. Instead, you can find <a href="https://wiki.openjdk.java.net/display/OpenJFX/Building+OpenJFX" target="_blank">instructions</a> that will allow us to build our own version. **The easiest way is to use versions built by other users**. I used the one provided on the <a href="https://github.com/javafxports/openjdk-jfx/issues/376" target="_blank">GitHub website</a>.  

Link to download: <a href="https://github.com/DrDEXT3R/Archive/raw/master/OpenJFX_x86/openjfx11_x86.zip" target="_blank">*openjfx11_x86.zip*</a> (all credit to @<a href="https://github.com/graynk" target="_blank">graynk</a>).  

**Some unofficial versions of JDK have JavaFX built in<sup>[1]</sup> (e.g. *Zulu 11.33.15* and *Liberica JDK 12.0.2*)**. You can check this by looking for the following jmod files in JDK directory:  
- *javafx.base.jmod*
- *javafx.controls.jmod*
- *javafx.fxml.jmod*
- *javafx.graphics.jmod*
- *javafx.media.jmod*
- *javafx.swing.jmod*
- *javafx.web.jmod*

## Getting Started
Let's start by running application from Command Prompt. As I mentioned before, **we will need the jar file of final program**. If our application does not use JavaFX platform or uses it, but it was built on JDK up to version 10, all we have to do is open Command Prompt, go to the location on the disk with the jar file of our application and enter the following command:
```java
java -jar CLP-Calculator.jar
```
<sup>[1]</sup> This command also works on some unofficial versions of JDK 11+.

In my case, the application is built using JDK 11 & OpenJFX. JavaFX is not part of JDK 11, so we have to indicate the location of our GUI creation platform in the command.
```java
java --module-path "C:\Program Files\Java\javafx-sdk-11.0.2\lib" --add-modules=javafx.controls,javafx.swing,javafx.fxml -jar CLP-Calculator.jar
```
As an explanation:
- *"module-path"* - provides information about the location of the module;
- *"add-modules"* - specifies additional root modules.

<br/>
Note!
1. *"javafx.swing"* is required in my case because CLP Calculator uses the class *"SwingNode"* in the code, which together with related *"SwingNodeHelper"* are part of the javafx.swing module. Therefore, to indicate that the class is to be used with a modulelepath and not with classpath, we must include this information in *"add-modules"*.  
If your program does not use the *"SwingNode"* class, you can skip this section. Conversely, if your program uses any class that should be used from the appropriate modulepath, add this information here.
2. A similar command was entered when setting the VM options in IntelliJ IDEA from the previous <a href="/java/your-javafx-app-on-jdk-11-&-openjfx/#ide-settings" target="_blank">post</a>.

## Jlink Tool  
To use jlink, we must **first find out which JDK modules the application uses**. For this purpose, we will use another tool called *"jdeps"*, which is also included in JDK.

Open the Command Prompt, go to the place where our jar file is and enter the following command:
```java
jdeps --list-deps CLP-Calculator.jar
```
<div style="text-align: center;">
    <img alt="result" src="/assets/images/blog/creating-custom-runtime-image/jdeps1.png">
</div>

Unfortunately, this command does not contain OpenJFX modules, so let's modify it:
```java
jdeps --module-path "C:\Program Files\Java\javafx-sdk-11.0.2\lib" --add-modules javafx.controls,javafx.swing,javafx.fxml --list-deps CLP-Calculator.jar
```
<div style="text-align: center;">
    <img alt="result" src="/assets/images/blog/creating-custom-runtime-image/jdeps2.png">
</div>

We can generate a runtime image now. To do this we have to:  
- put all displayed dependencies after *"add-modules"* (including *javafx.controls, javafx.swing, javafx.fxml*);  
- copy all jmod files from JavaFX SDK *("Java\javafx-sdk-11.0.2\lib\javafx-jmods-11.0.2")* to JDK directory *("Java\jdk-11.0.2\jmods")*. For <sup>[1]</sup> this is unnecessary.

```java
jlink --bind-services --no-header-files --no-man-pages --compress=2 --strip-debug --add-modules java.base,java.datatransfer,java.desktop,java.scripting,java.xml,javafx.graphics,javafx.controls,javafx.swing,javafx.fxml --output jre
```

To optimize the size of the generated JRE, I added a few more flags:
- *"no-header-files"* - excludes header files;
- *"no-man-pages"* - excludes man pages;
- *"compress=2"* - enable compression of resources ZIP;
- *"strip-debug"* - strips debug information from the output image;
- *"bind-services"* - link service provider modules and their dependencies. In my case this is required because there is a <a href="https://bugs.openjdk.java.net/browse/JDK-8210759" target="_blank">bug</a> in OpenJDK 11.

You can find more options <a href="https://docs.oracle.com/javase/9/tools/jlink.htm#JSWOR-GUID-CECAC52B-CFEE-46CB-8166-F17A8E9280E9" target="_blank">here</a>.

## Run Your App
**Custom runtime image was generated in the same folder as the jar file**. Let's move this *CLP-Calculator.jar* to *"jre\bin"* and try to run our application.
```java
java -jar CLP-Calculator.jar
```
<div style="text-align: center;">
    <img alt="result" src="/assets/images/blog/creating-custom-runtime-image/app.png">
</div>

**The entire *"jre"* folder can be moved to another computer with the right system architecture** (any 32-bit program runs on 64-bit) **and JDK installed** (Java Runtime Environment is not needed). In the next post I will show you how to make an **.exe** from our application, which you won't need even JDK to run.


