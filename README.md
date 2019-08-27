
## Name: Devina Sachin Dhuri

-----------------------------------------------------------------------
-----------------------------------------------------------------------
Following are the commands and the instructions to run ANT on the project.
#### Note: build.xml is present in multiThreadedHS/src folder.
To clean, compile and run, be sure to be in the multiThreadedHS folder.

-----------------------------------------------------------------------
## Instruction to clean:

### Command: ant -buildfile src/build.xml clean

Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------
## Instruction to compile:

### Command: ant -buildfile src/build.xml all

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run:

### Command:  ant -buildfile src/build.xml run -Dargs='N input1.txt input2.txt input3.txt output.txt <debugValue>'

eg: ant -buildfile src/build.xml run -Dargs='3 input1.txt input2.txt input3.txt output.txt 5'


-----------------------------------------------------------------------
## Description: Data Structures:
For storing the data local to each thread, ArrayList is used.
Similarly the shared data structure between hreads is an ArrayList too.

##### Time Complexity:
add(): O(1)  
get(): O(1)
remove(): O(n)   
contains(): O(n)  

-----------------------------------------------------------------------

### References and Citations:
1. QuickSort:
https://codereview.stackexchange.com/questions/181391/sorting-an-arraylist-of-vehicles-using-quick-sort
2. MErgeSort:
https://www.codexpedia.com/java/java-merge-sort-implementation/
