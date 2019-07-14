---
title: "Your JavaFX App on JDK 11 & OpenJFX"
date: 2019-07-07
categories:
    - Java
tags:
    - JavaFX
header:
    image: "/assets/images/blog/your-javafx-app-on-jdk-11-&-openjfx/main-image.png"
    teaser: "/assets/images/blog/your-javafx-app-on-jdk-11-&-openjfx/main-image-teaser.png"
    og_image: "/assets/images/blog/your-javafx-app-on-jdk-11-&-openjfx/main-image-teaser.png"
excerpt: "With the advent of Java 11, there were several key changes. One of them is the disconnection of JavaFX from JDK. JavaFX is a technology for creating a graphical user interface that was created as a replacement for Swing and AWT. From Java 8 this is the recommended library for creating a GUI."
---

## Introduction
With the advent of Java 11, there were several key changes. One of them is **the disconnection of JavaFX from JDK**. JavaFX is a technology for creating a graphical user interface that was created as a replacement for Swing and AWT. From Java 8 this is the recommended library for creating a GUI. After the release of version 11, you can download it independently. From the developer's point of view, **the most important thing is to include JavaFX in your project**.  

Another significant change is the release of **two versions of Java**:
- the one that can be downloaded from the <a href="https://www.oracle.com/technetwork/java/index.html" target="_blank">official Oracle website</a> - it can be used free of charge only for non-commercial purposes; 
- OpenJDK to download at <a href="https://jdk.java.net" target="_blank">jdk.java.net</a> - it can be used free of charge even for commercial purposes (GPL license).

**These versions are practically identical**. The difference is that OpenJDK has much shorter support (6 months from the release date) than the Oracle version (several years). If we want our version of Java to receive any security patches (in the case of commercial applications) for a longer period of time, we have to choose the first option with a monthly subscription fee.

## OpenJDK Installation on Windows
1.	<a href="https://jdk.java.net/archive/" target="_blank">Download</a> JDK as a zip file
2.	Extract and move it to any place
3.	Open *“Control Panel”* and then *“System”*
4.	Click *“Advanced System Settings”*
5.	Select *“Advanced”* and then *“Environment Variables”*
6.	Create new system variable  
• Variable name: *JAVA_HOME*  
• Variable value: installation path of the JDK e.g. *C:\jdk-11.0.2*
7.	Edit *“Path”* variable and add *"%JAVA_HOME%\bin"*
8.	Open *"Command Prompt"* and enter *"java -version"*  
You should see something like that:
<div style="text-align: center;">
    <img alt="result" src="/assets/images/blog/your-javafx-app-on-jdk-11-&-openjfx/cmd.png">
</div>


## OpenJFX Installation on Windows
1.	<a href="https://gluonhq.com/products/javafx/" target="_blank">Download</a> *“JavaFX Windows SDK”*
2.	Extract to your specified folder

## IDE Settings
When we download and install all the necessary files, we can take care of our application. We use this method when migrating application to the new version of JDK or creating a completely new project. The whole will be shown on the example of **IntelliJ IDEA**.

1.	Open an existing project or create a new one
2.	Set project SDK  
*File →&nbsp;Project&nbsp;Structure →&nbsp;Project →&nbsp;Project SDK*  
• Select the folder with the OpenJDK location  
• Check if the *"Project language level"* is the same as the SDK version
3.	Add OpenJFX as a library  
*File →&nbsp;Project&nbsp;Structure →&nbsp;Libraries →&nbsp;+ →&nbsp;Java*  
• Select the folder with the *javafx-sdk\lib* location
4.	Add a global path to JavaFX SDK  
*File →&nbsp;Settings →&nbsp;Appearance&nbsp;&&nbsp;Behavior →&nbsp;Path&nbsp;Variables →&nbsp;+*  
• Name: *PATH_TO_FX*  
• Value:  select the folder with the *javafx-sdk\lib* location
5.	Set VM options  
*Run →&nbsp;Edit Configurations*  
• Paste the following text:  
```java
--module-path ${PATH_TO_FX} --add-modules=javafx.controls,javafx.fxml
```
  
<br/>
After completing the above steps, your application should work on OpenJDK & OpenJFX.