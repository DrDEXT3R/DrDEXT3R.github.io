---
title: "Jar2Exe #2: Wrap JAR and JRE Together"
date: 2020-01-10
categories:
    - Java
tags:
    - JavaFX
    - Jar2Exe
header:
    image: "/assets/images/blog/wrap-jar-and-jre-together/header-image.png"
    teaser: "/assets/images/blog/wrap-jar-and-jre-together/header-image-teaser.png"
    og_image: "/assets/images/blog/wrap-jar-and-jre-together/header-image-teaser.png"
excerpt: "This tutorial will allow you to pack the result of your recent work with the application (JAR) into one EXE file. Thanks to that, a portable version will be created, ensuring the launch of your program on computers without JDK and JRE installed."
---

<table>
  <tr>
    <th>Part 1</th>
    <th><a href="/java/creating-custom-runtime-image" target="_blank">Creating Custom Runtime Image</a></th>
  </tr>
</table>

## Introduction
In the last <a href="/java/creating-custom-runtime-image/" target="_blank">post</a> I showed how to generate a custom runtime image. This tutorial will allow you to **pack** the result of your recent work with the application (JAR) **into one EXE file**. Thanks to that, a **portable version will be created**, ensuring the launch of your program on computers **without JDK and JRE installed**.

## Launch4j
The tool that will allow you to create an executable application on Windows is called ***Launch4j*** (download <a href="https://sourceforge.net/projects/launch4j/" target="_blank">here</a>). To run this cross-platform executable wrapper you need *Java Runtime Environment 1.6.0 - 1.8.9*, so if the correct version have not installed on computer, you must do it. 

<div style="text-align: center;">
    <img alt="result" src="/assets/images/blog/wrap-jar-and-jre-together/launch4j_error.png">
</div>

Remember also about the **right order in the environment variables**, thus ensuring program launch using the correct JRE. In case of any problems I refer you to my post on a similar <a href="/java/your-javafx-app-on-jdk-11-&-openjfx/#openjdk-installation-on-windows" target="_blank">topic</a>.

## Getting Started
The first step will be to create two directories: **"app"** containing **"bin"**. Then the previously generated **custom runtime image** (called *"jre"*) **will be moved to the *"bin"* along with the JAR file**. In addition, you can paste an ICO image if you want to change the icon of your application.
  
The *"app"* directory tree will look like this:  
<div style="text-align: center;">
    <img alt="result" src="/assets/images/blog/wrap-jar-and-jre-together/tree.png">
</div>

Note!  
If you follow my previous post, *"jre"* also has the same JAR file, so delete it.

## Create EXE
Run *Launch4j* and fill in the appropriate fields:
- output file
- jar 
- icon (optionally)

<div style="text-align: center;">
    <img alt="result" src="/assets/images/blog/wrap-jar-and-jre-together/basic.png">
</div>

- bundled JRE path (note the lack of "\\" at the beginning)
- "64-bit" checkbox (optionally)

<div style="text-align: center;">
    <img alt="result" src="/assets/images/blog/wrap-jar-and-jre-together/jre.png">
</div>

Now press *"build wrapper"* and name the configuration file.

<div style="text-align: center;">
    <img alt="result" src="/assets/images/blog/wrap-jar-and-jre-together/build.png">
</div>

If all goes well, delete the unnecessary JAR and CFG files. The *"app"* directory is **a portable version of the application** that can be run on other computers. 
