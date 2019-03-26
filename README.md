# CS207 Sem2 Cryptography 

Project Complete... 


| Stage           | Mark          |
| --------------- | ------------- |
| User Stories    | 18/24         |
| Class Diagrams  | 17/18         |
| Sprint One      | 13/23         |
| Sprint Two      | 23/29         |
| Sprint Three    | 23/29         |
| Final Report    | tbc           |
| Total           | tbc           |

### Road Map
Here is how far we are through the project, and how far we still have to go.

#### Sprint One 
+ ~~As a player I want to be able to generate a cryptogram so I can play it~~ 

+ ~~As a player I want to be able to enter a letter so I can solve the cryptogram~~

+ ~~As a player I want to be able to undo a letter so I can play the cryptogram~~

Still some bugs in this stage but it might be close enough for the purposes of the task. Mark given: `13/23`. A large amount of marks where lost for lack of JUnit testing. 


#### Sprint Two
+ ~~As a player I want to be able to save a cryptogram so I can play it at another time~~
     + ~~Write Solution, User Input, solution mapping and current mapping to a file~~
     + ~~Implement a feature where this data can be updated and overwritten when the user progresses and want to save their progress~~
     + ~~Look into creating a new file for this data or using the current stats file~~

+ ~~As a player I want to be able to load a cryptogram so I can continue a game I was previously playing~~
     + ~~Verify that the user has a saved game for them to play~~
     + ~~Use Buffer Reader to bring that data into the current game. Maybe create a new constructor for a game object??~~
     + ~~Look into the difficulties of recreating hash maps that have been saved to a file~~

+ ~~As a player I want to store my player name so the software can track my game play statistics~~
     + ~~Write player stats to a file~~
     + ~~Create a new instance of the player class using stats from the file~~
     + ~~Update stats when player has finished game~~

+ ~~As a player I want the software to track the number of cryptograms I have successfully completed~~
     + ~~Add increment setter to the end of the while loop of when a game object is running~~

+ ~~As a player I want the software to track the number of cryptograms I have played so I can see how many games I’ve attempted~~

+ ~~As a player I want the software to track the number of correct guesses I have made so I can see how accurate I am as a percentage of my total number of guesses~~ 
    + ~~Add increment setter to the end of the while loop of when a game object is running~~

+ ~~As a player I want to load my details so I can track my game play statistics~~ 

##### TO-DO 
+ ~~Most important - Work on a method which can replace a line in a file~~
+ ~~Save/Load a game~~
+ ~~This sprint is almost done, Need to fix accuracy, which is currently working but not returning the correct numebr.~~
+ ~~We need to create several JUnit Tests so we don't get bad marks again!~~
+ Update a players guesses before they exit the game.
+ Improve robustness (Catch if a file has been tampered with/ corrupted)
+ Imrprove usability of the game



#### Sprint Three
+ ~~As a player I want to be able to show the solution so I can see the answer to a cryptogram I can't solve~~

+ ~~As a player I want to be able to see the frequencies of all the letters in the cryptogram so I can compare this withe the frequencies of English~~ Letters

+ As a player I want to be able to see the top 10 scores for number of successfully completed cryptograms 
     +Singleton design pattern thing?

+ ~~As a player I want to be able to get a hint for a letter so I can solve the cryptogram~~

##### TO-DO 
+ Write the report
+ More JUnit Testing for the three new user stories 
+ Refactor code to achieve better code quality 




#### Things that would be nice to add 
+ GUI (probably not worth the effort)
+ Create a JAR
+ Look at methods of clearing the screen to create the illusion of changing pages.

