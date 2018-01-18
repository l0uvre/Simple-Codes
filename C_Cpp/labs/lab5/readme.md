
# LAB5
We'll extend in this lab the Lab 4 UTF8string class by adding overloaded 
operators. 

You are asked to redefine: 
 - [ ] `<<`
 - [ ]   `+`  that will become regular concatenation (if two objects are called u1 and  u2, u1 + u2 changes neither u1 nor u2) 
 - [ ] ` += ` to append something (u1 += u2 changes u1, not u2) 
 - [ ] `*` for repeating a string n times (if u is "àéèç", u * 2 or 2 * u should return 
"àéèçàéèç", u unchanged ) 
 - [ ] `!`  for reversing a string (original string unmodified), which means 
reversing the characters (not the bytes!), for instance if u is "étudiant" 
(student in French), !u should be "tnaiduté". 

A test program is supplied (you should this time modify the .hpp  and decide 
whether your operators should be methods or friend functions) 
