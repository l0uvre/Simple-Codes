#include "userprog/syscall.h"
#include <stdio.h>
#include <syscall-nr.h>
#include <list.h>
#include "threads/interrupt.h"
#include "threads/thread.h"
#include "threads/malloc.h"
#include "threads/vaddr.h"
#include "threads/interrupt.h"
#include "devices/shutdown.h"
#include "devices/input.h"
#include "filesys/filesys.h"
#include "filesys/file.h"
#include "userprog/pagedir.h"
#include "process.h"
#include "syscall.h"

#define ITF_ARG(f, i, TYPE) ((TYPE)(get_arg(f, i)))
#define ITF_ARG_POINTER(f, i, TYPE, len) ((TYPE)get_arg_pointer(f, i, len))


static void syscall_handler (struct intr_frame *);

static void halt (void);
static tid_t exec (const char *cmd_line);
static int wait (int pid);
static bool create (const char *file, unsigned initial_size);
static bool remove (const char *file);
static int open (const char *file);
static int filesize (int fd);
static int read (int fd, void *buffer, unsigned length);
static int write (int fd, const void *buffer, unsigned length);
static void seek (int fd, unsigned position);
static unsigned tell (int fd);
static void close (int fd);

static int get_arg (struct intr_frame *f, int i);
static void *get_arg_pointer (struct intr_frame *f, int i, int len);
static void check_pointer (void *p);
static struct user_file *find_open_file(int fd);

static int FD_COUNT = 2;

void
syscall_init (void)
{
  lock_init (&fileSystem);
  intr_register_int (0x30, 3, INTR_ON, syscall_handler, "syscall");
}

static void
syscall_handler (struct intr_frame *f)
{
  int id = ITF_ARG(f, 0, int);
  uint32_t ret = 45466468;

  switch (id) {
    case SYS_HALT:                   /* Halt the operating system. */
        halt();
        break;
    case SYS_EXIT:                   /* Terminate this process. */
        exit (ITF_ARG(f, 1, int));
        break;
    case SYS_EXEC:                   /* Start another process. */
        ret = exec (ITF_ARG_POINTER(f, 1, const char *, -1));
        break;
    case SYS_WAIT:                   /* Wait for a child process to die. */
        ret = wait (ITF_ARG(f, 1, int));
        break;
    case SYS_CREATE:                 /* Create a file. */
        ret = create (ITF_ARG_POINTER(f, 1, char *, -1), ITF_ARG(f, 2, unsigned));
        break;
    case SYS_REMOVE:                 /* Delete a file. */
        ret = remove (ITF_ARG_POINTER(f, 1, char *, -1));
        break;
    case SYS_OPEN:                   /* Open a file. */
        ret = open (ITF_ARG_POINTER(f, 1, char *, -1));
        break;
    case SYS_FILESIZE:               /* Obtain a file's size. */
        ret = filesize (ITF_ARG(f, 1, int));
        break;
    case SYS_READ:                   /* Read from a file. */
        ret = read (ITF_ARG(f,1, int), ITF_ARG_POINTER(f,2,void *, ITF_ARG(f,3,int)), ITF_ARG(f,3,unsigned));
        break;
    case SYS_WRITE:                  /* Write to a file. */
        ret = write (ITF_ARG(f,1, int), ITF_ARG_POINTER(f,2,const void *, ITF_ARG(f,3,int)), ITF_ARG(f,3,unsigned));
        break;
    case SYS_SEEK:                   /* Change position in a file. */
        seek (ITF_ARG(f, 1, int), ITF_ARG(f, 2, unsigned));
        break;
    case SYS_TELL:                   /* Report current position in a file. */
        ret = tell (ITF_ARG(f, 1, int));
        break;
    case SYS_CLOSE:                  /* Close a file. */
        close (ITF_ARG(f, 1, int));
        break;
    default:
        exit (-1);
  }

  if (ret != 45466468) {
    f->eax = ret;
  }
}

static void
halt () {
  shutdown_power_off ();

}


void
exit (int status) {
  struct thread *t = thread_current ()->parent_thread;
  if (t) {
    struct thread_child *tc = thread_set_child_exit_status (t, thread_tid (), status);
    if (tc) {
      sema_up (&tc->semaphore);
    }
  }
  printf("%s: exit(%d)\n", thread_current()->name, status);
  thread_exit ();
}

static tid_t
exec (const char *cmd_line) {
  tid_t processID = process_execute (cmd_line);
  return processID;
}

static int
wait (int pid) {
  return process_wait (pid);
}

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

static int
open (const char *file) {
  int res;
  lock_acquire (&fileSystem);
  struct file *f = filesys_open (file);
  if (f) {
    struct user_file *uf = malloc (sizeof(struct user_file)); 
    uf->f = f;
    uf->fd = FD_COUNT++;
    res = uf->fd;
    list_push_back (&thread_current ()->open_files, &uf->elem);
  } else {
    res = -1;
  }
  lock_release (&fileSystem);
  return res;

}

static int
filesize (int fd) {
  int res = -1;
  lock_acquire (&fileSystem);
  struct user_file *uf = find_open_file (fd);
  if (uf) {
    res = file_length (uf->f); 
  }
  lock_release (&fileSystem);
  return res;

}

static int
read (int fd, void *buffer, unsigned length) {
  if (fd) {
    int res = -1;
    lock_acquire(&fileSystem);
    struct user_file *uf= find_open_file (fd);
    if(uf) {
      res = file_read (uf->f, buffer, length);
    }
    lock_release((&fileSystem));
    return res;

  } else {
    unsigned i;
    char *p = (char*)buffer;
    for(i = 0; i < length; i++) {
      *p = input_getc();
      p++;
    }
    return length;
  }

}

static int
write (int fd, const void *buffer, unsigned length) {
  if (fd == 1) {
    putbuf (buffer, length);
    return length;
  } else {
    int res = -1;
    lock_acquire(&fileSystem);
    struct user_file *uf= find_open_file (fd);
    if (uf) {
      res = file_write (uf->f, buffer, length);
    }
    lock_release((&fileSystem));
    return res;
  
  }
  return -1;
}

static void
seek (int fd, unsigned position) {
  lock_acquire(&fileSystem);
  struct user_file *uf= find_open_file (fd);
  if (uf) {
    file_seek (uf->f, position); 
  }
  lock_release((&fileSystem));
}

static unsigned
tell (int fd) {
  unsigned res = 0;
  lock_acquire(&fileSystem);
  struct user_file *uf= find_open_file (fd);
  if (uf) {
    res = file_tell (uf->f); 
  }
  lock_release((&fileSystem));
  return res;

}

static void
close (int fd) {
  lock_acquire(&fileSystem);
  struct user_file *uf= find_open_file (fd);
  if (uf) {
    file_close (uf->f);
    list_remove (&uf->elem);
    free (uf);
  }
  lock_release((&fileSystem));
}

static int
get_arg (struct intr_frame *f, int i) {
  int *p = (int*)(f)->esp + i;
  check_pointer (p);
  check_pointer ((char*)p + 3);
  return *p;
}

static void *
get_arg_pointer (struct intr_frame *f, int i, int len) {
  void **p = ((void **)(f)->esp) + i;
  check_pointer (p);
  check_pointer ((char *)p + 3);
  check_pointer (*p);
  //check_pointer ((char *)*p + 3);

  char *ret = (char *)*p;
  check_pointer (ret);

  if (len == -1) {
    while (*ret) {
      check_pointer (ret++);
    }
  } else {
    check_pointer (ret + len);
    while (len >= 0) {
      check_pointer (ret++);
      len--;
    }
  }

  return *p;
}

static void
check_pointer (void *p) {
  if ((unsigned int) p >= (unsigned int) PHYS_BASE) {
    exit (-1);
  }

  if (pagedir_get_page (thread_current ()->pagedir, p) == (void *)0) {
    exit(-1);
  }
}

static struct user_file *
find_open_file (int fd) {
  struct list_elem *e; 
  struct list *l = &(thread_current ()->open_files);
  for (e = list_begin (l); e != list_end (l); e = list_next (e)) {
    struct user_file *uf = list_entry (e, struct user_file, elem);
    if (uf->fd == fd) {
      return uf; 
    } 
  }
  return NULL;
}
