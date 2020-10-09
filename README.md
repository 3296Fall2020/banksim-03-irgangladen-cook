# BankSim
The race condition may occur because a thread could be interrupted while withdrawing or depositing from an account. The balance is saved under currentBalance in both methods (Account.deposit and Account.withdraw), but if the thread gets interrupted after this line, the balance may change. When the interrupted thread starts up again, it does it's math based on a previous balance, which may be too large or too small, and therefore creates or destroys money.  

# Requirements
The requirements for this assingment was implementing a variety of tasks to get the program to run asynchronously with a variety of threads and methods to allow the threads to run without being interuppted by the other threads running. We implemented everything that was asked, but there are a few problems that could be improved upon. For the first task we implemented the UML sequence diagram. For the second task we implemented protection code to resolve the race condition. For the third task we implemented a new separate thread so it can provide code protection on the newly created threads. The fourth task we implemented a wait and notify solution so the transfer would be defered until after the account balance becomes greater than the trasnferring amount. Finally, for the fifth task we implemented a close bank so it can stop the deadlock.

# Teamwork
The collaboration was pretty good. We as a team split the work up in half and properly worked on our parts and came together when we wanted to implement them to the main branch. We both split the tasks evenly and helped each other when needed. The coding was roughly the same between the two of us. Talia did task 2, task 4, and task 5. Wayne did task 1, task 3 and helped out with the other tasks too. It was pretty even work. We both revised the work that was given by the both of us.

# Testing
We tested the program with a test thread that was created to look at the problems and errors that was given. We had manual tests at first then that changed to automated after the test thread was created. We discovered a ton of problems and bugs when we were testing and implementing our code in, but we found a way through them. We both wrote the test and associated code together. The test thread was written after most of the code was done to ensure what was going on.

## UML Sequence Diagram
![GitHub Logo](https://github.com/3296Fall2020/banksim-03-irgangladen-cook/blob/master/UMLSequenceDiagram.png)
