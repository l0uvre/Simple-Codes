# Lab3

``` bash
$ make
$ Usage: program < file_to_analyze.txt
```
It will display the language block with the most characters belong to, which is to say, pop the info about the utf-8 texts.

1) Define a suitable structure to load all this in an array (size 300 is big enough)
2) Write a function to search this array when provided with a Unicode value, and a small test program.
You are provided with code that does Unicode/UTF-8 conversions
Read a file from the standard input  - that means that your program will be called like this:
./your_program < name_of_file_to_analyze
Your program must display on the standard output the name of the block to which most characters belong (there may be characters from different blocks)
