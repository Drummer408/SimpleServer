# SimpleServer
THIS IS A WORK IN PROGRESS. THIS IS NOT THE FINAL VERSION AND TESTING/BUG FIXING IS STILL TAKING PLACE.

## Introduction
A small scale web server that performs basic mathematical computations. The purpose of this project is to expand upon core network programming skills and experiment with multi-threaded client/server applications. This project is completed without the use of third party networking libraries in order to gain a deeper insight on what exactly is happening "behind the scenes".

The functions provided by this web server are:
* add
* subtract
* multiply
* divide

## Syntax
In order to run the client, you must first make sure the server is running. Once everything is running appropriately, the client will display a prompt similar to that of a unix shell --> "$ ". When you see this prompt, the system is waiting for you to enter data. Proper syntax is as follows:

```
<math_function> <argument_1> <argument_2> ... <argument_n>
```
* <math_function> should be replaced with any of the mathematical functions listed above. Invalid spelling or a function that does not exist will return an error message to the user.
* <argument_1> ... <argument_n> refers to the argument list. Arguments must be real numbers, otherwise an error message will be returned to the user. You can have as many arguments as you want, but they MUST be separated by spaces and you MUST enter at least two real number arguments.

Because only one math function can be entered at a time, order of operations is not supported.
