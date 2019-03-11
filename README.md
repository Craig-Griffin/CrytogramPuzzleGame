# CS207 Sem2 Cryptography 

### Road Map
Here is how far we are through the project, and how far we still have to go.

#### Sprint One 
+ ~~As a player I want to be able to generate a cryptogram so I can play it~~ 

+ ~~As a player I want to be able to enter a letter so I can solve the cryptogram~~

+ ~~As a player I want to be able to undo a letter so I can play the cryptogram~~

Still some bugs in this stage but it might be close enough for the purposes of the task.


#### Sprint Two
+ As a player I want to be able to save a cryptogram so I can play it at another time
     + Write Solution, User Input, solution mapping and current mapping to a file
     + Implement a feature where this data can be updated and overwritten when the user progresses and want to save their progress
     + Look into creating a new file for this data or using the current stats file

+ As a player I want to be able to load a cryptogram so I can continue a game I was previously playing
     + Verify that the user has a saved game for them to play
     + Use Buffer Reader to bring that data into the current game. Maybe create a new constructor for a game object??
     + Look into the difficulties of recreating hash maps that have been saved to a file

+ As a player I want to store my player name so the software can track my game play statistics
     + ~~Write player stats to a file~~
     + ~~Create a new instance of the player class using stats from the file~~
     + Update stats when player has finished game

+ ~~As a player I want the software to track the number of cryptograms I have successfully completed~~
     + ~~Add increment setter to the end of the while loop of when a game object is running~~

+ ~~ As a player I want the software to track the number of cryptograms I have played so I can see how many games I’ve attempted~~

+ ~~As a player I want the software to track the number of correct guesses I have made so I can see how accurate I am as a percentage of my total number of guesses~~ 
    + ~~Add increment setter to the end of the while loop of when a game object is running~~

+ ~~As a player I want to load my details so I can track my game play statistics~~ 


We need to create several JUnit Tests so we don't get bad marks again!

TO-DO 
+ Most important - Work on a method which can replace a line in a file
+ Save/Load a game


#### Sprint Three
+ ~~As a player I want to be able to show the solution so I can see the answer to a cryptogram I can't solve~~

+ ~~As a player I want to be able to see the frequencies of all the letters in the cryptogram so I can compare this withe the frequencies of English Letters

+ As a player I want to be able to see the top 10 scores for number of successfully completed cryptograms 

+ ~~As a player I want to be able to get a hint for a letter so I can solve the cryptogram~~
