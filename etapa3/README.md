# Proiect GlobalWaves  - Etapa 3

#### Assignment Link: [https://ocw.cs.pub.ro/courses/poo-ca-cd/teme/proiect/etapa3](https://ocw.cs.pub.ro/courses/poo-ca-cd/teme/proiect/etapa3)


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

# Implementation

## Flow
The general flow of the app is as follows:
1. The Library and MoneyManager singletons are instantiated
2. The Library instance is filled with the input data (songs) from the base input file
3. The commands are read from the respective input file (e.g. "test04")
4. The commands are processed one by one by calling processCommand()
5. The processes return responses, which are added to the output and subsequently written to the output file

## Primary classes
Library is the main class which holds onto most of the data, with audio collections and users being held inside. This is where stuff like songs and playlists are retrieved whenever requested by a command. Being a sole database, it is initiated in a single instance with the Singleton pattern.

MoneyManager, added in this third stage, is a monetization-focused database which also uses a Singleton pattern. It stores user and artist money-related classes, which themselves store certain values like how much an artist has made from their songs, or how many songs a user has listened to while premium (for the purposes of later distributing the money to the artists listened to). It also is called upon at the end of the program with a special one-time command to compile and display all of the top artists on the platform.

Command is an abstract class from which all the different commands are inherited. This is central to the Command pattern used to deal with processing the various inputs and outputting the responses from them. The command package contains all of these different, specific classes with their varied functionalities.

User is a class which holds individual user data. It stores stuff like a the notifications and subscribers, user's MusicPlayer, artist's and host's albums and events and whatnot. Though initially intended to be a super class from which Artist and Host are extended, time constraints have made the temporary hack of putting artist and host specific stuff inside of the User class, a more... permanent solution. Let this be a lesson of what tech debt is.

MusicPlayer itself is another class initially meant solely for the user, made to play music and handle audio functions such as skipping a song, creating a new playlist, etc.
Due to some aforementioned tech debt, it now also contains usertype-specific wrapped stats, which are all described in the [given design document. (etapa3)](https://ocw.cs.pub.ro/courses/poo-ca-cd/teme/proiect/etapa3)

WrappedFactory is, lastly, a rather important class that is called upon in the CommandWrapped class. As the name suggests, it is a factory which tries to create a WrappedData object. Based on the user provided (haha Strategy pattern) it correctly calls the usertype-specific construction method, which itself uses the builder pattern methods present within WrappedData to build and subsequently return the newly created object.

## Improvements
- Due to how a lot of stuff is hastily implemented, some classes are a bit too intertwined, causing a lot of readability to really be bad. Improvements (as noted in feedback) could be made by creating more special methods/classes to deal with these functionalities.
- User, Artist and Host should be split, possibly increasing both readability and code efficiency as not so many references will need to be kept.
- More exceptions! No exceptions!