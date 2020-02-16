---
title: "HGC #1: Hand Gesture Control - Overview"
date: 2020-02-16
categories:
    - Python
tags:
    - Hand Gesture Control
    - Machine Learning
header:
    image: "/assets/images/blog/hand-gesture-control-overview/header-image.png"
    teaser: "/assets/images/blog/hand-gesture-control-overview/header-image-teaser.png"
    og_image: "/assets/images/blog/hand-gesture-control-overview/header-image-teaser.png"
excerpt: "Nowadays, technological progress boils down to making life easier for people in every aspect. The devices perform more tasks for us, while offering a number of new functionalities. One of them, more and more commonly encountered, is communication with the equipment without the use of standard remote controls or items intended for this."
---

<table>
  <tr>
    <th>GitHub</th>
    <th><a href="https://github.com/DrDEXT3R/HandGestureControl" target="_blank">DrDEXT3R/HandGestureControl</a></th>
  </tr>
</table>

## Introduction
Nowadays, **technological progress boils down to making life easier for people in every aspect**. The devices perform more tasks for us, while offering a number of new functionalities. One of them, more and more commonly encountered, is interaction with the equipment without the use of standard remote controls or items intended for this. Instead, **built-in microphones and cameras are used to provide control commands**.

The advantages of **speech control** can be seen in everyday life. Making a phone call using voice while preparing breakfast, or checking the weather for the rest of the day while choosing outfit are just some of them. At the same time, it also has its consequences. We limit ourselves to using devices that do not make any sounds when speaking the command. We can imagine **turning to a TV or an audio amplifier** in order to increase the volume, but we must remember that **their work is also caught by the microphone**. In such cases, complicated algorithms are needed that will be able to distinguish between overlapping voices. 

A good solution for such problems is the use of **hand gesture recognition**. Specific combinations of finger positions will allow **sending commands quietly and controlling a device** at the same time.

## Aim of Project
The goal is to create a program that will be able to **recognize hand gestures on an ongoing basis**, shown to an average webcam. To visualize the correctness of the project's work, it was decided that application would run on a computer and the gestures performed would **control video player**.

<div style="text-align: center;">
    <img alt="result" src="/assets/images/blog/hand-gesture-control-overview/work.png">
</div>

## Division of Labor
The work will be divided into 3 stages:
* Creating your own dataset.  
This part will consist of writing a program to capture the image frame from the webcam at the right time, transform into a specific form and give it the appropriate class - a kind of hand gesture.
* Creating a neural network.  
This stage will be based on modeling and learning a neural network, consisting of convolutional layers, which will be able to recognize the kind of hand gesture.  
* Creating a computer control program.  
This part will be composed of writing a program to capture the image frame, recognize the hand gesture (using the previously learned neural network) and on this basis will force the computer to perform the appropriate action.

## Workplace
In my case, the entire project along with learning the neural network will be implemented on a laptop with the **Ubuntu** operating system installed. This computer has:  

### Hardware  
* 0.3 MP webcam  
* Intel Core i7-3610QM  
* NVIDIA GeForce GT 630M
* 8 GB RAM  

### Software
* <a href="https://docs.python.org/3/" target="_blank">Python 3</a> - The programming language
* <a href="https://www.tensorflow.org/api_docs/python/" target="_blank">TensorFlow</a> - A end-to-end open source platform for machine learning 
* <a href="https://keras.io/documentation/" target="_blank">Keras</a> - A high-level neural networks API written in Python
* <a href="https://docs.opencv.org/4.1.1/d6/d00/tutorial_py_root.html" target="_blank">OpenCV</a> - Computer vision and machine learning software library
* <a href="https://numpy.org/doc/1.17/" target="_blank">NumPy</a> - The package for scientific computing with Python
* <a href="https://github.com/xianyi/OpenBLAS/wiki" target="_blank">OpenBLAS</a> - An optimized BLAS library (Basic Linear Algebra Subprogram)
* <a href="https://pynput.readthedocs.io/en/latest/" target="_blank">pynput</a> - This library allows control and monitor input devices
* <a href="https://docs.h5py.org/en/stable/" target="_blank">h5py</a> - The package is a Pythonic interface to the HDF5 binary data format
* <a href="https://docs.python.org/3/library/os.html" target="_blank">os</a> - This module provides a portable way of using operating system dependent functionality  
  
## Prerequisites
Install before starting work:  
* VLC media player
* Python 3
```console
sudo apt-get update
sudo apt-get upgrade
sudo apt-get install python3.6
sudo apt-get install python3-pip
```  
* All necessary dependencies (<a href="https://raw.githubusercontent.com/DrDEXT3R/HandGestureControl/master/requirements.txt" target="_blank">download</a>)
```console
pip3 install -r requirements.txt
```  
* BLAS library
```console
sudo apt-get install build-essential cmake git unzip pkg-config libopenblas-dev liblapack-dev
```  
* HDF5 library
```console
sudo apt-get install libhdf5-serial-dev python3-h5py
```  

In addition, you can install **CUDA drivers** and **the cuDNN toolkit**. Remember that **your graphics card must support CUDA**.
1. <a href="https://developer.nvidia.com/cuda-downloads" target="_blank">CUDA</a>
2. <a href="https://developer.nvidia.com/cudnn" target="_blank">cuDNN</a>  
