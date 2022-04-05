# MazeSolver

This application asks users to load a .png image of a maze, with specified starting and ending points, and solves the maze using a depth-first search algorithm.

This was a project I built during my senior year of high school using Java and the IntelliJ IDE. The goal of the project was to gain an understanding of breadth-first search and depth-first search algorithms, and also to strengthen my understanding of recursion, which I especially struggled with when I was first learning Java.

# Project Status

Project is currently unfinished. I've listed in "TODOs" some possible future improvements, however I don't currently have any plans to develop this project further. 

# Instructions

Clone this repository.

Before executing the project, draw your maze. Make sure:
- The starting point is a single pixel with RGB value (0, 255, 0).
- The ending point is a single pixel with RGB value (255, 0, 0).
- The walls are black, i.e. RGB value (0, 0, 0).
- These RGB values have to be exact or the program will complain!
- There should only be one starting pixel and one ending pixel!
- Save the maze as a .png.

Here's an example maze:

![enlargedmaze](https://user-images.githubusercontent.com/66391744/161664871-b4096d4b-4a5d-40bd-946d-b49c21bf5bcd.png)

*Note: This image has been enlarged from its original resolution of 32x32*

Run MazeSolverDriver.java and enter the maze file name when prompted.

The output file will be called "solvedMaze.png" and should look like:

![enlargedsolvedMaze](https://user-images.githubusercontent.com/66391744/161665002-f392a5cc-0ccd-4d24-91c2-7762ae17bb59.png)

After that you're done!

# TODOs
While I don't have any plans to continue improving this project, here are some features I'd like to add:
- A menu that allows users to specify the kind of algorithm (breadt-first or depth-first) used to solve the maze. Currently the program's default algorithm uses depth-first search.
- Errror-handling for invalid filenames.
- Account for more complex mazes. E.g. program should recognize starting/ending points that are slightly different shades of green or red, walls that aren't exactly black, etc.
- Optimize the current algorithms I've written, or explore different algorithms to solve the maze.
