## Project 2 User Program Report

### Task 1: Argument Passing

Description: The “process_execute(char *file_name)” function is used to create new user-level processes in Pin-
tos. Currently, it does not support command-line arguments. You must implement argument passing,
so that calling “process_execute("ls -ahl")” will provide the 2 arguments, ["ls", "-ahl"], to the
user program using argc and argv.


#### Data structures and functions

The entire command is passed as string and PintOS has already provided the very handy function
char *strtok_r (char *, const char *, char **) to split the string by some specific delimiter and save the address info to a pointer.

Such data struct is chosen to accomplish this task.

```c
struct process_arg {
  char **save_ptr;
  char *file_name_tok;
  char *to_free;
};
```


#### Algorithms

Noted that process_execute() function will call the thread_create() with start_process() function as a parameter, and another parameter is the struct process_arg which is shown above. Mainly the code needs to be modified is in the process.c

```c
tid = thread_create (cmd_name, PRI_DEFAULT, start_process, &args);
```
Then I go through the start_process() function in which another function load() seems so vital.

```c
// success = load (file_name, &if_.eip, &if_.esp);
 success = load (args->file_name_tok, args->save_ptr, &if_.eip, &if_.esp);
```
Naturally, continue with the load() function. And the most important thing for this task is the line below.

```c
/* Set up stack. */
if (!setup_stack ((char *)file_name, save_ptr, esp))
  goto done;
```
To complete the argument passing on PintOS, Correctly setuping up the stack with parameters are important. In the setuo_stack() function, the code split the string into several parts with the first as the command name, others as parameters by the strtok_r() call.

```c
int i;
for (i = 0; res != NULL; i++) {
  int r_len = strlen(res) + 1;
  *ep -= r_len;
  memcpy(*ep, res, r_len);
  res = strtok_r(NULL, " ", save_ptr);
}
```

Referencing the pintOS documentation, the pointer *esp is supposed to be calculated carefully to fit the stack frame and proper order of accessing user memory.

#### Synchronization
For the very first part, no resources was shared between processes or threads, for it only call the thread_create() function. More synchronization problems need to be considered in later parts.


### Task 2: Process Control Syscalls
Simple description:

Pintos currently only supports one syscall: exit. You will add support for the following new syscalls:
halt, exec, wait, and practice. Each of these syscalls has a corresponding function inside the user-level
library, lib/user/syscall.c, which prepares the syscall arguments and handles the transfer to kernel
mode. The kernel’s syscall handlers are located in userprog/syscall.c.

#### Data structures and functions
Functions to implement:

```c
void halt (void);
tid_t exec (const char *cmd_line);
int wait (int pid);
void exit (int status);
```
Among all of them above, wait and exit system call need careful design, and the final version of code modifies some struct member in the threads/thread.h and does implementation in threads/thread.c.

```c
struct thread_child{
    struct semaphore semaphore;
    tid_t id;
    int status;
};

struct thread
{
  ............
    struct list child_list;
    struct lock child_list_lock;
    struct thread *parent_thread;
  .............
#ifdef USERPROG
    /* Owned by userprog/process.c. */
    uint32_t *pagedir;                  /* Page directory. */
#endif

    /* Owned by thread.c. */
    unsigned magic;                     /* Detects stack overflow. */
};
```


#### Algorithms
Considering that in the context of pintOS, one process only occupies one single thread. And in thread.h, the interface thread_exit() is defined. So it's natural to modify the code in thread_exit() to implement exit() in syscall.c. By skimming the testing files and considering the wait() system call, it's easy to think about that one process may have lots of childs running and one process may or not have a parent process. Because of thread type which is well defined in PintOS, the code is modified by inserting a child thread list and a lock for synchronization, as thread is not so different from process in PintOS.

When exit() is called, then go to the parent process, and let the parent kill the child process with required status.

halt() and exec():
just call the related functions in process.
```c
void
halt () {
  shutdown_power_off ();
}

tid_t
exec (const char *cmd_line) {
  tid_t processID = process_execute (cmd_line);
  return processID;
}
```

wait():
To implement wait() system call, the process_wait() is handy to use.
Firstly, find the child process by passing current process struct and child process id. Secondly, get the status information from child thread and remove it from parent process's list. Finally, free the memory of this child process. A lot of work is done by calling helper functions written in thread.c and others provided in kernel library.


#### Synchronization
As shown above, the child thread struct has a member of semaphore and thread struct has a lock on child thread list. They probably guarentee less occurrence of race conditions.



### Task 3: File Operation Syscalls

Description: In addition to the process control syscalls, you will also need to implement these file operation syscalls:
create, remove, open, filesize, read, write, seek, tell, and close. Pintos already contains a basic
filesystem. Your implementation of these syscalls will simply call the appropriate functions in the file
system library. You will not need to implement any of these file operations yourself.

The Pintos filesystem is not thread-safe. You must make sure that your file operation syscalls
do not call multiple filesystem functions concurrently. In Project 3, you will add more sophisticated
synchronization to the Pintos filesystem, but for this project, you are permitted to use a global lock on
filesystem operations, to ensure thread safety. We recommend that you avoid modifying the filesys/
directory in this project.

#### Data structures and functions
Continue works on syscall.c to implement create, remove, open, filesize, read, write, seek, tell, and close system calls.

syscall.c:
```c
bool create (const char *file, unsigned initial_size);
bool remove (const char *file);
int open (const char *file);
int filesize (int fd);
int read (int fd, void *buffer, unsigned length);
int write (int fd, const void *buffer, unsigned length);
void seek (int fd, unsigned position);
unsigned tell (int fd);
void close (int fd);
```

As described in the required doc, a lock on the file system call is sufficient for this project.
In syscall.h, define this lock:
```c
struct lock fileSystem;
```

And in the thread and child thread struct, files opened by process should deny addition writes on themselves so records on open and executed files by each process are need. Add some member to these two structs, which is shown below.

thread.h:
```c
struct thread_child{
    struct semaphore semaphore;
    tid_t id;
    int status;
    struct file *t_file;
    struct list_elem elem;
};

struct thread
{
  ............
    struct list child_list;
    struct lock child_list_lock;
    struct thread *parent_thread;
    struct list open_files;
  .............
#ifdef USERPROG
    /* Owned by userprog/process.c. */
    uint32_t *pagedir;                  /* Page directory. */
#endif

    /* Owned by thread.c. */
    unsigned magic;                     /* Detects stack overflow. */
};
```

#### Algorithms

As PintOs already has a minimum file system, some file system could be implemented simply call related functions with synchronization under consideration. Like these:

```c
static bool
create (const char *file, unsigned initial_size) {
  bool res;
  lock_acquire (&fileSystem);
  res = filesys_create (file, initial_size);
  lock_release (&fileSystem);
  return res;
}

static bool
remove (const char *file) {
  bool res;
  lock_acquire (&fileSystem);
  res = filesys_remove (file);
  lock_release (&fileSystem);
  return res;
}
```

Others like read, write, filesize, close and tell system calls need to find the specific file by file descriptor and do operations on them. Some helper functions are added to achieve this. A function checks the current process's holding file list, and returns the file by file descriptor number. In additionaly, read and write are supposed to support the particular situations when file descriptor is 0 and 1, standard in and standard out.

open system call will allocate a file struct and assign the open file struct with a file descriptor. And then add the element to the thread's file list.

When a thread exits, all resources it may use must be released. The code is in the thread.c (`thread_exit()` function)

Additionaly, all system call is conveyed to and handled by `syscall_handler()` in syscall.c.

#### Synchronization
A file system lock avoid most synchronization problems, maybe not so efficiently but sufficient for this project as described by requiement docs.


### Code Quality

- Does your code exhibit any major memory safety problems (especially regarding C strings), memory
leaks, poor error handling, or race conditions?

    Every `malloc()` is followed by `free()`. And pointer checker is implemented carefully.

- Did you use consistent code style? Your code should blend in with the existing Pintos code. Check
your use of indentation, your spacing, and your naming conventions.

  I try my best to make the code style consistent, like indent, naming.

- Is your code simple and easy to understand?

  Perhaps

- If you have very complex sections of code in your solution, did you add enough comments to explain
them?

  Maybe

- Did you leave commented-out code in your final submission?

  Probably no.

- Did you copy-paste code instead of creating reusable functions?

  The later one.
- Are your lines of source code excessively long? (more than 100 characters)

  Nope.
- Did you re-implement linked list algorithms instead of using the provided list manipulation

  No, just use the library.




### Summary

PintOS is really hard but also useful for students to get their hands dirty on OS concepts. By writing the code, one can have the chance to comprehend more on awesome
fundamental ideas behind operating systems.
