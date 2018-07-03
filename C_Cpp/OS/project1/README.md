Project 1: Threads
====================================



## Task 1
第一个任务是将timer_sleep()函数的忙等待实现为更加有效的方式，由于timer_msleep等系列的函数调用了timer_sleep() 只需更改timer_sleep()的实现即可。

为此，在thread.h里中 为struct thread增加一个成员像下面这样：
```c
struct thread
{
......
   int64_t sleep_ticks;
......

}

```

修改相应的线程初始化函数，为sleep_ticks赋值为零。

考虑到在thread.c 里有struct list ready_list这样的就绪队列，可以在timer_sleep里面，将当前的线程加入到在timer.c里创造的一个sleeping_list，将timer_sleep()函数的线程，加入这个有序睡眠队列，相关API kernel里的list.h很方便的提供了。然后将这个线程阻塞。

然后再中断函数 timer_interrup()中，检查当时的tick数，遍历这个有序队列，以从小到大等待的tick数排列的有序队列，当这个线程需要等待的tick数小于当前的tick数，就将其从sleeping_list移出，然后调用thread_unblock()函数，重新调度。


这样就pass掉了几乎所有的alarm-*测试，除了alarm_priority。

然后修改next_thread_to_run, 用list的API，每次找到优先级最大的线程，这样就PASS了所有的alarm*测试集


## Task 2
实现RoundRobin的调度算法，由于pintOS本身就是RR的，只需要修改每个线程的时间片与其优先级相关即可。


## Task 3
这个任务主要解决线程同步时的优先级问题，防止出现inverse priority的问题，即一个高优先级的线程去等待低优先级线程占有的锁。主要思路是实现线程的优先级捐赠，当占有锁的线程的优先级小于当前等待锁的线程的优先级，则发生捐赠，为此，修改数据结构。
在synch.h中修改lock结构体：

```c
/* Lock. */
struct lock
  {
    struct thread *holder;      /* Thread holding lock (for debugging). */
    struct semaphore semaphore; /* Binary semaphore controlling access. */
    struct list_elem elem;       /* list element for threads.            */
    int priority;               /* The maximum priority of threads waiting for this lock. */
  };
```

在thread.h中修改thread 结构体：

```c
struct thread
  {
    .....................
    int priority;                       /* Priority. */

   .................
    struct list locks;                  /* List lock held by this thread. */
    struct lock *waiting_lock;          /* The lock this thread waitinf for.
    .........................
  };

```

随后在修改相应的init_thread()和init_lock()函数，
修改lock_acquire(), lock_release()函数，实现优先级捐赠，通过list_max () 加上一些根据list.h提供的API接口，定义一些优先级的比较函数， 修改相应的cond_signal()函数为优先级最高的优先，也是通过list_max()来实现。

然后就通过了pintOS的官方测试


然后提交的代码是按照助教要求的，修改了时间片的大小，优先级的递减等等，然后官方测试结果并不理想。
