# LAB6
You are asked in this assignment to write a pair of processes checking each other. 
Many companies are relying on programs the only purpose of which is checking that 
everything is OK (service reachable, etc.) and to report immediately if something 
goes wrong. Although these programs don't do any other useful work, it's vital that 
they are extremely robust and always there, because otherwise you might discover 
problems when everybody has already noticed. 
We want a pair of father and child processes based upon the beeper program with the 
following behaviour: 


You are asked to redefine: 
 - [ ]  The father process forks a child and beeps 
 - [ ]    The child only watches the father every 0.5s (check function usleep()) 
 - [ ]  If the child process dies the father process forks another child to check it.
 - [ ]  If the father dies the child process forks a new child and assumes the role of 
the father (it beeps) 
 - [ ] Both processes stop if any of the two receives SIGTRAP 
