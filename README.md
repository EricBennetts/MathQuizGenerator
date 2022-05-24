# MathQuizGenerator
## Generates math quizzes according to your specification. Then you can answer the questions and mark them.

“Generator.java" takes as input a text file named "Requirements.txt". In this text file, you may specify what kind of questions you want. 
First, you can tell the program how many questions you would like to have. 
Second, you can specify the range of the operands. If you want them to range from 1 to 100, type 1. Else the program will generate operands ranging from 1 to 500. 
Third, you can determine whether floating point numbers and/or mixed numbers should be used. 1 for yes and 0 for no.
After running this program, you will get another text file called "Quiz.txt" containing questions according to your specification.

"Calculator.java" takes as input two text files: "Quiz.txt", and "Requirements.txt". Actually the second one is used only to determine how many questions are there. After running this program, it will output a text file "Answer.txt" containing the questions as well as the answers.

"Marker.java" takes as input three text files: "Answer.txt", "MyAnswer.txt", and "Requirements.txt". This program will determine whether the answers in "MyAnswer.txt" are correct or not. Then it will output a text file "FinalPaper.txt" with all the questions marked.
