# Proiect GlobalWaves  - Etapa 2

#### Assignment Link: [https://ocw.cs.pub.ro/courses/poo-ca-cd/teme/proiect/etapa1](https://ocw.cs.pub.ro/courses/poo-ca-cd/teme/proiect/etapa1)


## Skel Structure

* src/
  * checker/ - checker files
  * fileio/ - contains classes used to read data from the json files
  * main/
    * Main - the Main class runs the checker on your implementation. Add the entry point to your implementation in it. Run Main to test your implementation from the IDE or from command line.
    * Test - run the main method from Test class with the name of the input file from the command line and the result will be written
      to the out.txt file. Thus, you can compare this result with ref.
* input/ - contains the tests and library in JSON format
* ref/ - contains all reference output for the tests in JSON format

## Documentation

Keeping in line with the README of the previous stage, this documentation will cover the flow of the app and its unfortunate downfalls in the implementation.

### Implementation
Building up on the aforementioned stage's code, the app now includes more features as detailed in the assignment link, such as the ability to switch connection status to online and offline, the ability to add new albums (and by extension songs) and much more!

Just like before, the commands are processed and their responses are returned to the objectMapper to be written as output. Polymorphism is still used greatly, especially in the case of the newly added Album, which is in fact sharing a lot of funcionality with a normal Playlist. As such, it inherits the Playlist class while building on it.

### Improvements

A few upgrades have been made since the last implementation (such as a proper reorganization of the packages, keeping the visibility high) but still as far as possible improvements go:
* a proper rework of the interface ISelectable is certainly due, as functions used solely by collections should not be present (and unimplemented) for an interface which may be used by collections and non-collections alike
* greater differentiation between User, Artist and Host, possibly by making the latter two subclasses of the first one, as to not make the User class too hefty (time has not been on my side)
* checking for usage in the case of deletion commands should be optimized, as the worst offenders (i.e. DeleteUser) definitely hinder performance
* better error handling in case of exceptions should be looked into, as a lot of commands don't handle the case where a user might not have a player associated with them (should never happen anyway, all users generate with one)
  The last point has been made in the previous README as well, but the proposition for more error handling still holds true.