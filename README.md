# PhotoBrightness
[EN]

This is a Java program which modifies the luminosity of a picture. It contains a producer-consumer model of parallelization. Producer method is used for extracting the pixels from
the image then sending a quarter of the resulted matrix to consumer; the consumer method is used to convert the pixels into RGB values which are modified by adding or lowering
its value. These 2 are connected using a buffer class, which is used as a shared memory, so it contains syncronasation (wait + notify) (preventing the critical zone to be 
accessed by both threads at the same time).
The algorithm requires 4 parameters which can be read from keyboard or command line: 
  - the path to the folder where is the image
  - the name of the image
  - the name of the new image
  - a brightness adjustment level [-100,100]
After the algorithm runs a new image is created with the adjusment applied and in console it is shown the times of reading, transfering, editing and writing the data.
The algorithm tests 3 more features implemented as a test:
  - varargs test
  - 3 level inheritance of an abstract class
  - interface test
  
*How to modify the brightness level?*

well, I used ImageIO to extract the image than saved it as a matrix of pixels. I send quarters of this matrix through the buffer into the consumer; there I imported Color class
which can convert my pixel into red green blue values. these values can be modified by increasing or decreasing its values (thats how to change  luminosity). lastly a new matrix is
created with the new pixels which results in the final image.

Exemples and times in the .pdf file.
