# BankSim
The race condition may occur because a thread could be interrupted while withdrawing or depositing from an account. The balance is saved under currentBalance in both methods (Account.deposit and Account.withdraw), but if the thread gets interrupted after this line, the balance may change. When the interrupted thread starts up again, it does it's math based on a previous balance, which may be too large or too small, and therefore creates or destroys money.  

## UML Sequence Diagram
![GitHub Logo](https://github.com/3296Fall2020/banksim-03-irgangladen-cook/blob/task1/UMLSequenceDiagram.png)
