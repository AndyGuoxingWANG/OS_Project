I wrote the code by myself.

In the Task1, I implement the sychronized keyword to achieve mutual exclusion in the critical section and prevent race conditions and set up the server restrictions as described in the PA2 guide.
I think the most difficult problem in task 1 is figuring out the logic of restrictions.

In the Task2, I implememt the condition variable to avoid busy-waiting. At first, you should figure out the different conditions in the connect and disconnect. Then you implement await(),and signal() for solving the busy-waiting situations.
