# My Functions - OPP_EX1
This repository include all source files of Ex_1

This project is about drawing a functions at the GUI.
It includes many class but the most important is the following:

# 1. The Monom Class:
This class represents a simple "Monom" of shape a * x^b, a is a real number and b is an integer (summed a none negative).
The class implements the interface function and support simple operations as: construction, value at x, derivative, add and multiply.

# 2. The Polynom Class
This class represents a Polynom with add, multiply functionality, it also support the following:
1. Riemann's Integral.
2. Finding a numerical value between two values (currently support root only f(x)=0).
3. Derivative
This class is based on the Monom class.

# 3. The Complex Function Class:
This class make the following operations on functions: addition, subtracting , multiplying, finding the maximum
function between the functions, finding the minimum function between the functions, and the last oparation it's make a composition between functions.
The minimum and the maximum is deppend on the value at giving x to the function.
This class is based on the Monom and Polynom classes.

# 4. The Functions_Gui;
This class drawing a Coordinate System of a giving functions , it also support a 3 other 
operations and they: read functions from a file to functions collection and draw them, save functions to file
from the functions collection, and drawing the functions with a parameters for the GUI from JSON file.
 
*To more information go to the WiKi page of the Progect*
