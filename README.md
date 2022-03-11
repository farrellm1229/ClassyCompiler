# Classy Compiler
Professor Labouseur, please grade the branch titled "Project2". 

Long story short, the CST doesn't work correctly (in MOST cases, in some cases it works perfectly!). I have created some cstTest files to showcase the good and the bad concrete syntax trees, as well as explained some things in the comments of those files. For the parseTest (2 and 4 specifically) files, the CST's are far far far from being correct. For example, in my CCTree file, I have a case switch statement to go through the different types of tokens and create the corresponding CST. Let's say I have case: "a" for assignment statement, and in the test file I have {a = 3 print(a)}$. The CCTree file recognizes the case: "a" in the print statement, and adds nodes for the assignment statement in the middle of the print statement. For this reason, I have only allowed creation of nodes for assignment statements with the case: "z". This is one example, and I'm sure you can see how this can snowball into some very, very ugly CST's. BUT, on the bright side, for programs that do not overlap the CST's are perfect! (cstTest3 for example)

This is my repository where I will be designing and building a compiler for the class CMPT432.

I will be using Java as my programming language.

Testing will be achieved with the compilation command
  > javac CCMain.java 

Input will be taken as a command line parameter, e.g.,
  > java CCMain sampleProgram(s).txt

and write output to standard out.
