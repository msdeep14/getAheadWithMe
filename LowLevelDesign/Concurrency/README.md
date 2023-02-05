# CONCURRENCY CONCEPTS
Concepts around multi-threading, concurrency and parallelism. 

NOTE --
1. Few of the concepts can be specific to JAVA.
2. The information is compiled here basis my understanding by reading multiple blogs/oracle docs/videos and is mostly suitable for interview preparation. You can explore more on internet for further deep dive.

### Process
Think of process as sequence of steps to be performed to achieve something.
1. Process has separate memory allocated space. It don't share memory with other processes. This ensures complete isolation of process from each other, one process's state don't impact what's happening in another process.
2. Processes interact with each other via Inter process communication mechanisms such as Shared Memory or Message passing.
3. Processes take more time in creation, termination, context switching. Why? because process has its own Process Control Block(PCB), stack, address space. Separate data segment and code segment is created at each process level.
4. Process states - NEW, READY, RUNNING, WAITING, TERMINATED, SUSPENDED.
5. Process creation requires OS API call - fork(). [java.lang.Process](https://docs.oracle.com/javase/1.5.0/docs/api/java/lang/Process.html) for JAVA.
6. Processes don't require synchronization between them unlike threads where synchronization needs to explictly managed to avoid unwanted issues.

### Thread
Think of thread as sub step of a process(thread can be referred as light-weight process). Multiple threads can be created inside a process
to carry out specific actions. 
1. Threads share the memory space which makes them less latent.
2. If one thread is blocked within a process, it will also impact other threads in the same process.
3. Each thread in process has their own stack and register.

#### Thread Types
1. User Level Thread: Threads managed by users and created via user-level libraries such as JAVA Thread library.
2. Kernel Level Thread: Threads managed by kernel. Kernel-level threads are created via OS system calls.

NOTE - The blocking operation varies basis type of thread. 
For user-level thread, entire process is blocked if any of the user-level thread perform blocking operation.
This is not the case with kernel-level thread, blocking operation by one kernel thread don't impact execution of another thread.

### Java Virtual Threads
1. Light-weight implementation of Java threads. 1 Java Thread == 1 OS thread, which is resource intensive.
2. Virtual threads store their stack frames in Java's garbage-collected heap rather than in monolithic blocks of memory allocated by the operating system.
3. A good read - [infoq article](https://www.infoq.com/articles/java-virtual-threads/).

### Deadlock
A process/thread is in waiting state because it is waiting for a resource that is held by another process P2. P2 is waiting on another 
resource held by P3. P3 is waiting for another resource held by P1. All the processes are waiting on each other without being progressing 
eventually leading to deadlock situation.

**Deadlock conditions:** 
All four conditions should satisfy for system to be in deadlock state -
1. Mutual Exclusion: resources can't be shared by multiple processes at the same time.
2. Hold and Wait: holding resource and waiting for other resource.
3. No Preemption: resources acquired by process can only be released by itself once execution completes.
4. Circular Wait: processes waiting on each other.

#### Deadlock Handling
##### Deadlock Prevention
Don't let system enter into deadlock state by avoiding any of the four conditions specified in above section.

##### Deadlock Avoidance
The resource request is analyzed before-hand to ensure if it can cause any deadlock situations in the future. To perform this analysis, 
it requires all the information in advance around how resources will be requested/used in the future.
Deadlock avoidance algorithm - [Banker's algorithm](https://en.wikipedia.org/wiki/Banker%27s_algorithm).

##### Deadlock Detection
1. Check process state for deadlock in system.
2. If `isDeadlock == true`, apply algorithm for deadlock resolution.
   1. Process Termination: Abort the processes involved in deadlock. This might result in program failures as process might be partially completed. Instead of all process abort, system can look for processes which are of low priority or are running for too long in the system.
   2. Resource Preemption: resources are preempted and allocated to other processes until deadlock is resolved.

### LiveLock
The threads/processes are not blocked as in deadlock situation but the state keep on changing from one to another and not making any further progress.
**Example:** Queue Message processing: Queue consumer failed with exception and it puts back to queue in hope that it will processed next time but it
keeps on failing again and again with same exception(Specific to this example, solution could be to keep a upper limit on number of retries for specific
failure if msgs are put into dead-letter queue. Failed msgs should anyway never be put in the same queue again for re-processing).

### Starvation
Process in on hold because resources are not allocated to it. This could happen if resources keep getting allocated to high priority threads and low priority threads keep on waiting.

Some ways to avoid starvation situation -
1. Age of process - If process is too old, it should be given priority for resource allocation even though it is low priority task.
2. Multi-level feedback queue - [geeksforgeeks article](https://www.geeksforgeeks.org/multilevel-feedback-queue-scheduling-mlfq-cpu-scheduling/)

### Mutex vs Semaphore
#### Semaphore
Semaphores can be used to manage access of limited resources(via counting semaphores) or to resolve critical section problem(via binary semaphores).

Refer [SemaphoreImpl class](./src/semaphoremutex/SemaphoreImpl.java) for sample example.

#### Mutex
`Mutex = Mutual Exclusion`. It is **similar** to binary semaphore which ensures only one thread can acquire it and access the critical section.
1. Mutex is a locking mechanism whereas semaphore is signalling mechanism. The context which acquired the mutex lock can only release it but this is not the case with semaphores.
2. The #1 can prove to be downside if mutex lock is not released as it can't be unlocked from different context than who acquired it leading to starvation.

### I/O Bound
Application is referred as I/O bound if it involves reading/writing from/to input/output system and waiting for information. 
The main disadvantage of such applications could be the application can be spending too much time in I/O operations 
without doing any actual operations.
Example - upload/download files.

### Blocking I/O vs Non-Blocking I/O
#### Blocking I/O
The request waits for server to complete its processing. In general terms, the request will wait for read or write before 
returning back meaning the thread will be blocked state until there is data available for read or ongoing write operation 
is fully completed.

Blocking I/O operations are available with java.io package.
1. One byte data at a time - `InputSteam` and `OutputStream`
2. Wrapper for streams - `Reader` and `Writer`.

#### Non-Blocking I/O
The request is executed immediately. There could be possibility that request is not processed as soon as it is received, if 
there are no available threads to process the request, 
the request is queued(to be processed at later point of time) and response is returned. 
Other way to look at is if there is ongoing write operation, the thread can perform any other operation.

In analogy to event-driven architecture, you can think of this scenario as communication between service A and service B. 
service A invokes service B API for some processing and gets a response that request is received by service B.
Once the request processing completes by service B, it can publish an event to SNS topic(or Kafka topic) 
to which service A is subscribed. service A can then perform any additional required processing basis Service B response.

Non-Blocking I/O operations are available with java.nio package.
1. Read chunks of data at a time - `Buffer`.
2. Mapping raw bytes to/from readable characters - `CharsetDecoder`
3. Outside world communication - `Channel`
4. Enable multiplexing on a SelectableChannel and provide access to any Channels which are ready for I/O - `Selector`.

### CPU Bound
Task/process execution is dependent on CPU without requiring any I/O operations.
Example - High Performance computing

### Thread-safe
Thread-safe class/method/codeBlock means a class/method/codeBlock that can used by multiple threads concurrently without 
any issues(example the output of threads should be same as they were processed sequentially).

### Volatile Keyword
Volatile keyword is used inside a class to make it thread-safe. It can be used for any variable such as value of it can be 
modified by different threads at same time without any problem.
1. It can be used for primitive or object types though it can't be used with classes/methods.
2. Volatile keyword enforces to always read variable value from main memory and it is not cached. Both reads and writes will be atomic.
3. Compiler don't perform any optimization and order of instructions is maintained.
4. It can be used as replacement of `synchronized` in specific cases. 

**NOTE 1 -** Visibility problem is created without the usage of volatile keyword. 
How? Any modification performed by a thread A is updated thread A's local cache so 
thread B will still assume previous value of any variable. 
This can be solved by volatile keyword as volatile enforces to read always from 
main memory and not from local thread cache.

**NOTE 2-** volatile can be used to resolve visibility practice, prefer to use it for setting flag variables. 
For compound operations or counters, prefer AtomicInteger/AtomicLong/AtomicReference.

### Atomic Integer
`java.util.concurrent.atomic.AtomicInteger`.
Refer the sample example and comparison of volatile, AtomicInteger and synchronized keyword in 
class [VolatileKeywordImpl](./src/volatilesample/VolatileKeywordImpl.java).

Operations available with AtomicInteger - 
1. incrementAndGet
2. decrementAndGet
3. addAndGet(int delta)
4. compareAndSet(int expectedValue, int newValue) - Type of optimistic locking.

**NOTE -** AtomicReference provides atomic operations on underlying object reference variable.

### Synchronized
Synchronized keyword over a method or block of code ensures only single threads access it at a time avoiding race conditions.

Refer [SynchronizeSample class](./src/volatilesample/SynchronizeSample.java) for example.

1. Any object in synchronized block is placed inside a lock(lock acquired on start of block and released at end of the block). The lock is internal monitor which is responsible for checking synchronization.
2. Synchronized keyword should not be used with constructors/variables.

### Locks
Locks can be used to ensure single thread access the critical section at a time. 
Think of lock as a guard to critical section and this guard provides key to enter the critical section and while leaving critical section, handover this key back to guard.
Java Lock is similar to synchronized block discussed in above section.

#### Reentrant Locks
`private static Lock lock = new ReentrantLock()`.

1. synchronized is implicit lock and locks are explicit. We need to specifically define variable to create a lock and use it in the code block.
2. You can acquire the lock in one method and release in another method. There is no such flexibility in synchronized keyword.
3. tryLock() method with Locks which can be used to check if lock is available. 
4. ReentrantLocks allows to call lock.lock() multiple times without calling unlock(). Finally you can unlock() for the number of times lock() was called. `getHoldCount()` can be used to check how many times to lock() is called.
5. To make the lock fair, use `lock = new ReentrantLock(true)`. This ensure longest waiting thread gets the lock. Fair locks can be little slower as we extra queue needs to be maintained based ageing. Unfair lock could be faster but it can potentially lead to thread starvation in some cases.
6. tryLock() doesn't honor lock fairness. To make it fair, use `lock.tryLock(0, TimeUnit.SECONDS)`.

Refer [LockSample class](./src/locksample/LockSample.java).

#### ReadWrite Locks
Reentrant locks can be inefficient if there are threads blocked which only want to read some data. 
ReadWriteLocks helps to optimize this kind of use-case.
1. Reentrant lock - one thread at a time.
2. ReadWrite lock - one writer thread at a time **OR** multiple reader threads at a time. Key Point - Both read and write threads will not be given permission simultaneously, either one of them can execute at a time.
3. ReadWrite lock can be efficient for use-cases which requires high number of reads and less writes in general.

Refer [ReadWriteLock class](./src/locksample/ReadWriteLock.java).

### Optimistic and Pessimistic Locking
**Pessimistic Locking-** Lock the object beforehand performing any operation. 
Java synchronized keyword is example of pessimistic locking. 
It has extra performance bottleneck as threads have to wait unless permitted to execute specific operation.

**Optimistic Locking-** As name suggests, in this approach you can go ahead and execute the operation with assumption 
that no other thread will interfere. It relies on collision detection and handle if detected via retry/failure. 
Java CompareAndSwap is example of optimistic locking.


### Two-Phase Locking
Locking and unlocking has two phases - 
1. Growing Phase - Locks are obtained but not released.
2. Shrinking Phase - Locks are released but no new locks are obtained.

This ensures serializability but can cause deadlock, starvation and cascading rollback. 

The last lock acquired in growing phase is referred as **Lock Point**. Lock Points are used to define serializability, 
example basis lock point you can identify which transaction comes first in series of transactions.


### Phaser, CountDownLatch and CyclicBarrier
Read more [here](https://www.oracle.com/technical-resources/articles/javase/concurrent-programming.html).

#### CountDownLatch
This is helpful in scenarios if there is requirement to initialize some piece of code before executing further system 
logic or other threads. 
Think of a scenario, where database connection to be established before other threads can actually carry out the db query operations.

Refer [CountDownLatchSample class](./src/countDownLatchPhaserCyclicBarrier/CountDownLatchSample.java).

#### CyclicBarrier
Set of threads wait for each other to reach a common barrier point. 
and once common point is reached, then all the threads can carry forward their execution. 

**Why cyclic?** The same barrier can be used again and again among the threads.

#### Phaser
It is similar to CountDownLatch with some added functionalities. 
Phaser allows dynamic number of threads that needs to wait for continuing execution, in countDownLatch, 
we specific fixed number of parties upon initialization.
1. register() - party can register itself
2. bulkRegister() - extra parties register in bulk
3. arrive() - parties can arrive and continue
4. arriveAndAwaitAdvance() - parties can arrive and await for other parties.
5. arriveAndDeregister() - Parties can arrive, deregister and continue.

### Conditions
Take an example of Thread-1 and Thread-2.
Thread-1 complete execution in two stages. Stage-1 and Stage-2. 
Stage-1 is executed and the execution of State-2 is dependent on completion of Thread-2.
In such scenarios, Thread-1 will put an additional condition `condition.await()` to put thread in blocked state. 
Once Thread-2 execution is completed, it will run `condition.signal()` or `condition.signalAll()` and with this signal
all the threads which are blocked on this condition will carry forward their execution.

This is similar to `monitor.wait()` and `monitor.notify()` in synchronized code block.

Refer [ConditionSample class](./src/locksample/ConditionSample.java).

### wait(), notify() and notifyAll()
`wait()`, `notify()` and `notifyAll()` can be used in replacement of conditions. They can be applied on any java object.
1. wait() - similar to myCondition.await()
2. notify() - similar to myCondition.signal()
3. notifyAll() - similar to myCondition.signalAll()

Refer [MyBlockingQueue class](./src/practice/producerconsumer/lockCondition/MyBlockingQueue.java) 
and [MyBlockingQueue2 class](./src/practice/producerconsumer/waitNotify/MyBlockingQueue2.java) for sample examples.

### ExecutorService
`1 Java Thread == 1 OS Thread`

How to run tasks in parallel? via using Thread class.
```agsl
for(int i=0;i<1000;i++) {
   Thread thread = new Thread(some Runnable);
   thread.start();
}
```
The above operation will be too expensive as 1000 threads are being created at the same time which could lead to system crash.

To solve this problem, you can create a pool of threads and submits the large list of tasks to them. 
Now the tasks can be picked up as there are free threads present in the thread pool which are being maintained in a BlockingQueue.

Example - ThreadPool size = 10, even though submitted task size = 1000, the system will execute max 10 tasks at a time.
```agsl
   ExecutorService service = Executors.newFixedThreadPool(10);
   for(int i=0;i<1000;i++) {
      service.execute(Runnable task);
   }
```

#### submit() vs execute()
| **submit()**                         | **execute()**                             |
|--------------------------------------|-------------------------------------------|
| return type is Future                | return type is void                       |
| Runnable task can be passed as input | Both Runnable and Callable can be passed. |

#### ThreadPool Types
1. FixedThreadPool - initialize thread pool with fixed number of threads.
2. CachedThreadPool - tasks are held in synchronous queue. It will check if there are already available free threads, if yes then use one from available else create a new thread and assign task to it. This threadPool kills the idle threads with lifecycle more than 60 seconds.
3. ScheduledThreadPool - tasks that are required to be submitted after certain delay or require scheduling. It stores the tasks in delayQueue which stores tasks basis when these tasks should be executed(priority order is basis that).
   1. service.schedule(Runnable, delay:10, SECONDS). Task will be enabled after 10 seconds.
   2. service.scheduleAtFixedRate(Runnable, initialDelay:15, period:10, SECONDS). First task will start after 15 seconds. Subsequent tasks will execute at initialDelay+period, then initialDelay+2*period, and so on...
   3. service.scheduleAtFixedDelay(Runnable, initialDelay:15, delay:10, TimeUnit.SECONDS). First task will start after 15 seconds. Once this is completed, second will start after 10 seconds of 1st task completion.
4. SingleThreadedExecutor - similar to FixedThreadPool with `numThreads=1`

#### Future
1. Placeholder for value that will arrive sometime in the future.
2. To get actual response of `submit()`, use `future.get()`. 
3. get() is blocking operation - it will wait until the thread result is returned. We can use `future.get(1000, TimeUnit.MILLISECONDS)` to specify a timeout value for it to be blocked state if result is not available.

#### Runnable vs Callable
Runnable return type is void whereas Callable can return a value.

Refer [RunnableVsCallable class](./src/practice/generalPatterns/RunnableVsCallable.java) for example.

#### CompletableFuture
1. Assume you've used a for loop to gather all the results from callables.
2. There is possibility that as your iterate over future list, not all futures are computed at a moment.
3. In such scenario, the thread will blocked for time being until the future result arrives for specific thread.
4. There can be a case in which you're waiting at 4th thread whose result is not computed yet but results for 10th and 11th thread are already in.
5. But since you're iterating in for loop, you'll still be blocked on 4th thread.
6. How can you overcome this?
   1. use timeout value on `future.get(1000, TimeUnit.MILLISECONDS)`
   2. use CompletableFuture to run threads independently.
   3. Use ExecutorCompletionService - helps in processing the threads as their execution completes.

CompletableFuture helps to run a series of tasks on a separate thread than the main thread. Consider an example of uploading a youtube video on [@MsDeepSingh](https://www.youtube.com/@MsDeepSingh) channel, what are the steps.
1. upload video file from local store.
2. run validation checks
3. upload in SD format
4. upload in HD format.

Now these steps should be executed one after another and also there could be multiple users who are uploading videos on their YT channels. CompletableFuture makes it easier to code such pattern with proper exception handling.

### ExecutorCompletionService
ExecutorCompletionService helps to retrieve response as Futures finish.
Refer [ExecutorCompletionServiceSample class](./src/practice/generalPatterns/ExecutorCompletionServiceSample.java) for example.

### ForkJoinPool
Achieve similar functionality to ExecutorService. ForkJoinPool is optimized for use-cases where tasks creates sub-tasks on the go. Example - mergesort.
As the name suggests, ForkJoin consists of two operations - 
1. fork - divide task into sub-tasks
2. Join - aggregate the result of sub-tasks.

**Per-thread queuing -** Each thread has their own deque, so all the sub-tasks will be part of this deque.

**Work-Stealing algorithm** Take a scenario of two threads, Thread-1 and Thread-2. In a scenario where Thread-1 created 
large number of sub-tasks and Thread-2 has very less number of sub-tasks. 
Once Thread-2 completes its subtasks, it will subtasks from Thread-1(from other end of the queue) to help out in processing of subtasks. 
In simple terms, available threads take up tasks from busy threads.

### Synchronous Queue
Synchronous queue is similar to blocking queue with size=1. Refer producer-consumer example to understand about blocking queue.

so why do we need a queue with size=1?
> There is direct handoff between producer and consumer. The item is not moved to queue. The producer will not be allowed 
> to put item into queue even though the queue is empty. The producer is only allowed to put item to queue once consumer requests for item.
> Well, technically item is not put into the queue. The item is passed on once consumer thread requests for it.

### Thread-safe Singleton pattern
Also referred as Double-Checked locking with Singleton.
Refer [ThreadSafeSingleton class](./src/practice/threadsafesingleton/ThreadSafeSingleton.java).

### ConcurrentModificationException
When collection is modified when already an operation is going on. Example, iterating over the list and at the same time removing of an element.

### Event Loop
TBU

### Monitors
Monitors are nothing but implementation of `synchronized` keyword in JAVA. Monitor is an abstraction that helps to 
achieve mutual exclusion between multiple threads trying to access a resource concurrently.

Additionally in JAVA, any object can act as a monitor utilizing `wait()` and `notify()` methods.
Refer [MyBlockingQueue2 class](./src/practice/producerconsumer/waitNotify/MyBlockingQueue2.java) for example.

### Practice
#### Producer Consumer Problem
1. Multiple producers and consumers.
2. Producer is responsible for generation of new tasks.
3. Consumer is responsible for executing the tasks created by producer.

Clarifying questions - 
1. What's the maximum number of tasks that producers can produce? Is there any upper cap? For this problem, assume producers will produce max n tasks and then wait for consumer to consume.
2. The scale of operation, how many producers? how many consumers? 

Approach - Producer
1. Create a FIFO queue with max size = n
2. Producer threads will put tasks to queue until full.
3. Block producer threads if queue capacity is full.
4. Publish a signal that queue is not empty.

Approach - Consumer
1. Get tasks from queue if queue is not empty.
2. Block consumer threads if queue is empty.
3. Publish a signal that queue is not full anymore once consumer fetches a task.

#### Dining Philosopher's Problem
Round dining table where philosophers are sat. There can be two states for them - 
1. Eating
2. Thinking

They need to make use of forks, philosopher need two forks adjacent to him and starts eating.
Problem? The forks are limited, with 5 philosophers there are only 5 forks but philosopher need two forks to start eating, so all philosophers can't each at the same time.

1. No philosophers who are adjacent to each other should not eat at the same time.
2. A philosopher may pick up only one fork at a time.
3. Philosopher can't pick fork from neighbour's hand.
4. Once philosopher is done eating, he puts back the forks on the table.

Why solve such problem? - Problem of resource utilization and how limited set of resources can be allocated to bunch of processes.

**Solution Approach**
1. Each fork is represented by semaphore.
2. Philosopher picks up fork and put corresponding semaphore to `await()` state.
3. Once done eating, philosopher releases both left and right fork with `signal()` operation.

```agsl
//binary semaphore, initialized to 0
Semaphore forks[5];

for philosopher i
do {
    //change semaphore value to 1 ---> java: semaphore[i].acquire();
    await(fork[i])
    await(fork[(i+1)%5])
    //eat
    
    //change semaphore value back to 0 ---> java: semaphore[i].release();
    signal(fork[i])
    signal(fork[(i+1)%5]);
    
    //think
} while(true);
```

Any issues with above approach? Deadlock situation - All the philosophers get hungry at the same time and pick up the left fork. Now all the philosophers are waiting for fork in the right and none of them is actually eating.

Potential solutions -
1. Allow only 4 philosophers at the table for the table of 5.
2. Allow philosopher to pick up a fork only if both forks are available. - execute code in critical section - Refer [wikipedia blog for Dijkstra's solution](https://en.wikipedia.org/wiki/Dining_philosophers_problem).
3. Asymmetric solution - odd philosopher picks up first left fork and right fork; even philosopher picks up first right fork and then left fork. This can also be achieved by - All the philosophers pick up their left fork except the last one, so essentially the 5th philosopher will pick right fork first and then the left fork. Refer this implementation in [DiningPhilosophers class](./src/practice/diningphilosopher/DiningPhilosophers.java).

### Practice
1. Leetcode problemset - https://leetcode.com/problemset/concurrency/
2. https://www.careercup.com/page?pid=threads-interview-questions
3. Refer to [src/practice folder for problem set](./src/practice).