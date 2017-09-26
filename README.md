131A-1 : Operating Systems Collection
================================================
    Author: Hao Wang (haowang@brandeis.edu)
    Date: 2016 FALL

---

LIST:

PA1_1:
Description:
It is going to be to build a basic command line environment for interacting with a file system. This command line environment (or shell) will be written in Java. The first part of this assignment is designed to refresh your coding ability in Java, and ensure that you have the coding pre-requisites to be able to complete this course. You will do this while learning about Unix and practicing your Java-based System calls.
####Details:
PA1_1_instruction.pdf
####Improvement:
1 test fails
---

PA1_2
Description:
In the second part of the assignment, I leverage my new knowledge about threads in Java to make your REPL loop's components (its filters) execute in separate threads, thus allowing the program run concurrently. Additionally, you will allow the commands to be executed as background processes.
####Details:
PA1_2_instruction.pdf

---

PA2_1
Description:
My simulation program will need to use Java default synchronized methods and busy waiting to enforce the rules limiting the resource use. Busy waiting can work well if the waiting is very short but can be inefficient if the waiting is long.
Details:
PA2_instruction.pdf

---

PA2_2
Description:
I modify the simulation program to avoid busy waiting and to provide FIFO order for consumer requests. I have a centralized controller that coordinates consumer access to multiple instances of the shared resources. 
Details:
PA2_instruction.pdf
Improvement:
You need to maintain a FIFO order. You should use condition variable for each basic server.
