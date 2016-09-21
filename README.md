# Shape Bakery

Group 1

--------

## Overview
ShapeID teaches children to identify shapes by presenting them with both abstract
representations and images of real­world objects. They select from multiple options the image
that most closely represents the shape they are tasked to find. Inversely, they may be asked to
select between multiple text­based options based on a single image representing the shape that
is to be identified. In both types of questions, the child will be able to receive helpful hints and
information to ensure they know exactly what characteristics of a shape they are looking for. At
the end of a game, children and their caretakers are presented with results showing a child’s
skill level and progress.



## Learning goals
Experience with the game will result in an enhanced ability to recognize shapes in real world
objects. Children will develop concentration and listening skills. Additionally, vocabulary will be
expanded upon due to the inclusion of captions that name the object in the image.


## Prerequisites
Children are expected to have the following skills before using the program:
1. Ability to use a computer or tablet with touch or mouse
2. Basic English skills with either written or aural comprehension


## Design & Implementation
The program will be implemented as a Java Applet.  
Knowledges required include:  
● Java  
● Java Applets  
● Swing UI Library  
● JavaScript Object Notation (JSON)  
● Git/GitHub  
● Doxygen  
Note: Bolded items indicate subjects that need to be learned by every member of the team. An
item that is not bolded indicates at least one member of the team has prior knowledge. This list
may be expanded during implementation.


## Module Overview
1. User Interface
2. Game Engine
3. Question/Answer
Module subdivision criteria:​ The project was subdivided into three modules based on the
concept of data hiding. Each module should be responsible for a task and “know” everything
involved with that task with minimal exposure or inter­dependency with any other module. The
UI module knows how to display data and collect user input. The game engine knows input
responses, user progress, program flow, and final results. The question/answer module
encapsulates question and answer data and knows correct answers.


### User Interface (UI)
● Provides a visual front end to the game engine.  
● Receives user mouse inputs corresponding to possible options in a particular stage in
the game, and passes that information along to the game engine for actual processing.  
● Displays user interface elements and other necessary information as requested by the
game engine. The user interface doesn’t know anything about the state of the game, it
simply displays what it is told to display.


### Game Engine (GE)
● Acts as the central module, facilitating communication between the other modules.     
● Controls the flow of the program throughout all stages.                                      
● Initializes data for the initial game, and all subsequent games.    
● Receives user input from the user interface and maps it to the appropriate function. This
can involve moving information down further into the QA module if it concerns question
and answer data.   
● Receives validation responses from the QA module, and prepares for the next stage in
the game, passing along needed data to the user interface for updating.
Questions/Answers (QA)   
● Encapsulates question and answer data.  
● Responsible for validating responses received for questions. Only the QA module knows
what the correct answers are to the current question.  
● Receives a request to validate the correctness of a question from the game engine, and
sends back the result of the validation. It is only concerned with validating and
processing question and answer data, anything else that needs to change in the game is
managed by the game engine and user interface.