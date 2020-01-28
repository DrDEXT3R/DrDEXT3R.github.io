---
title: "Hand Gesture Control"
excerpt: "It is a program that recognizes hand gestures shown to the webcam. Each frame is subjected to the process of creating a hand mask, which is then given as the input of a learned neural network."
header:
  image: /assets/images/portfolio/hand-gesture-control/hand-gesture-control.jpg
  teaser: /assets/images/portfolio/hand-gesture-control/hand-gesture-control-teaser.jpg
gallery:
  - url: /assets/images/portfolio/hand-gesture-control/demo1.gif
    image_path: /assets/images/portfolio/hand-gesture-control/demo1.gif
    alt: "Demo 1"
  - url: /assets/images/portfolio/hand-gesture-control/demo2.gif
    image_path: /assets/images/portfolio/hand-gesture-control/demo2.gif
    alt: "Demo 2"
  - url: /assets/images/portfolio/hand-gesture-control/masks.png
    image_path: /assets/images/portfolio/hand-gesture-control/masks.png
    alt: "Masks"
---

## Hyperlinks
<table>
  <tr>
    <th>GitHub</th>
    <th><a href="https://github.com/DrDEXT3R/HandGestureControl" target="_blank">DrDEXT3R/HandGestureControl</a></th>
  </tr>
</table>

## Description
It is a program that recognizes hand gestures shown to the webcam. Each frame is subjected to the process of creating a hand mask, which is then given as the input of a learned neural network to predict result. Therefore, it is possible to control the video player by perform the appropriate gestures.

<div style="text-align: center;">
{% include gallery caption="The gallery showing how the application works.<br/>Hand gesture recognition (1); controlling video player (2); hand masks generated in various conditions (3)" %}
</div>

## Built With
* <a href="https://docs.python.org/3/" target="_blank">Python</a> - The programming language
* <a href="https://www.tensorflow.org/api_docs/python/" target="_blank">TensorFlow</a> - A end-to-end open source platform for machine learning 
* <a href="https://keras.io/documentation/" target="_blank">Keras</a> - A high-level neural networks API written in Python
* <a href="https://docs.opencv.org/4.1.1/d6/d00/tutorial_py_root.html" target="_blank">OpenCV</a> - Computer vision and machine learning software library
* <a href="https://numpy.org/doc/1.17/" target="_blank">NumPy</a> - The package for scientific computing with Python
* <a href="https://github.com/xianyi/OpenBLAS/wiki" target="_blank">OpenBLAS</a> - An optimized BLAS library (Basic Linear Algebra Subprogram)
* <a href="https://pynput.readthedocs.io/en/latest/" target="_blank">pynput</a> - This library allows control and monitor input devices
* <a href="https://docs.h5py.org/en/stable/" target="_blank">h5py</a> - The package is a Pythonic interface to the HDF5 binary data format
* <a href="https://docs.python.org/3/library/os.html" target="_blank">os</a> - This module provides a portable way of using operating system dependent functionality

## License
This project is licensed under the GNU General Public License v3.0 - see the <a href="https://github.com/DrDEXT3R/HandGestureControl/blob/master/LICENSE" target="_blank">LICENSE</a> file for details.
