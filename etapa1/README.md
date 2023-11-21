# Proiect GlobalWaves  - Etapa 1

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

## Tests:
1. test01_searchBar_songs_podcasts - 4p
2. test02_playPause_song - 4p
3. test03_like_create_addRemove - 4p
4. test04_like_create_addRemove_error - 4p
5. test05_playPause_playlist_podcast - 4p
6. test06_playPause_error -4p
7. test07_repeat - 4p
8. test08_repeat_error - 4p
9. test09_shuffle - 4p
10. test10_shuffle_error - 4p
11. test11_next_prev_forward_backward - 4p
12. test12_next_prev_forward_backward_error - 4p
13. test13_searchPlaylist_follow ---  (+4)
14. test14_searchPlaylist_follow_error - 4p
15. test15_statistics - 4p
16. test16_complex - 10p
17. test17_complex - 10p

## Documentation

The project currently utilizes 3 stepping stones to achieving the proposed tests. The first one is the parser, which is already provided partly by our benefactors in the form of the jackson module. It manages parsing the input json to commands, and later the responses to output json.
Once the initial list of commands is formed from the parsed input (along with the library based on the already formed LibraryInput instance), the processCommand() method is called on each command in order. Each of the processes has an effect on the very large MusicPlayer unique to each user, and as the commands are processed their responses are written to the outputs array to later be transposed back into a json file on disk.

#### Polymorphism

Polymorphism is used to great extent for commands and responses, keeping their LOC sizes low by doing minimal checks before offloading the work to methods within MusicPlayer. MusicPlayer is unfortunately a behemoth as a side effect of this, most of the logic intricately tuned to it and happening solely within it.

#### Improvements

As far as improvements go:
* (within MusicPlayer) updatePlaying could be called from within the getter of music player to update it to the latest state before further processing can occur;
* further reorganization of the project structure could help with class visibility
* all command classes which extend from the base Command class should be named such that a consistent naming scheme is kept
* all response classes should be moved to a "responses" package
* further cleanup of the MusicPlayer code should be done to ensure proper compartmentalization of the methods and functions within
* better error handling in case of exceptions should be looked into, as a lot of commands don't handle the case where a user might not have a player associated with them (should never happen anyway, all users generate with one)

#### .GIT Note

The project was made with the 3 levels of difficulty in mind, and as such a single repository was created. The "etapa1" folder is not the root of the project repository, but instead a subfolder of it. A dummy .git file was added so the automated checker is happy.