show engine innodb status;




=====================================
2021-04-28 18:58:10 0x7f0bc434b700 INNODB MONITOR OUTPUT
=====================================
Per second averages calculated from the last 60 seconds
-----------------
BACKGROUND THREAD
-----------------
srv_master_thread loops: 775 srv_active, 0 srv_shutdown, 2429898 srv_idle
srv_master_thread log flush and writes: 2430673
----------
SEMAPHORES
----------
OS WAIT ARRAY INFO: reservation count 4627
OS WAIT ARRAY INFO: signal count 4210
RW-shared spins 0, rounds 4206, OS waits 2096
RW-excl spins 0, rounds 1753, OS waits 31
RW-sx spins 1, rounds 30, OS waits 1
Spin rounds per wait: 4206.00 RW-shared, 1753.00 RW-excl, 30.00 RW-sx
------------
TRANSACTIONS
------------
Trx id counter 755681
Purge done for trx's n:o < 755664 undo n:o < 0 state: running but idle
History list length 1538
LIST OF TRANSACTIONS FOR EACH SESSION:
---TRANSACTION 421164501869968, not started
0 lock struct(s), heap size 1136, 0 row lock(s)
---TRANSACTION 421164501871792, not started
0 lock struct(s), heap size 1136, 0 row lock(s)
---TRANSACTION 421164501868144, not started
0 lock struct(s), heap size 1136, 0 row lock(s)
---TRANSACTION 421164501870880, not started
0 lock struct(s), heap size 1136, 0 row lock(s)
---TRANSACTION 421164501872704, not started
0 lock struct(s), heap size 1136, 0 row lock(s)
---TRANSACTION 421164501866320, not started
0 lock struct(s), heap size 1136, 0 row lock(s)
---TRANSACTION 421164501875440, not started
0 lock struct(s), heap size 1136, 0 row lock(s)
---TRANSACTION 421164501869056, not started
0 lock struct(s), heap size 1136, 0 row lock(s)
---TRANSACTION 755671, ACTIVE 34 sec inserting
mysql tables in use 1, locked 1
LOCK WAIT 2 lock struct(s), heap size 1136, 1 row lock(s)
MySQL thread id 54563, OS thread handle 139688511293184, query id 4536065 192.168.0.48 root update
insert into t1(`id`,`name`) values(1,'zhangsan')
------- TRX HAS BEEN WAITING 34 SEC FOR THIS LOCK TO BE GRANTED:
RECORD LOCKS space id 729 page no 3 n bits 72 index PRIMARY of table `play`.`t1` trx id 755671 lock mode S waiting
Record lock, heap no 2 PHYSICAL RECORD: n_fields 4; compact format; info bits 0
 0: len 4; hex 80000001; asc     ;;
 1: len 6; hex 0000000b87cd; asc       ;;
 2: len 7; hex 7a000001401e17; asc z   @  ;;
 3: len 4; hex 616c6578; asc alex;;

------------------
---TRANSACTION 755667, ACTIVE 42 sec inserting
mysql tables in use 1, locked 1
LOCK WAIT 2 lock struct(s), heap size 1136, 1 row lock(s)
MySQL thread id 54562, OS thread handle 139688513955584, query id 4536017 192.168.0.48 root update
insert into t1 values(1,'alex') on duplicate key update name='alex'
------- TRX HAS BEEN WAITING 42 SEC FOR THIS LOCK TO BE GRANTED:
RECORD LOCKS space id 729 page no 3 n bits 72 index PRIMARY of table `play`.`t1` trx id 755667 lock_mode X waiting
Record lock, heap no 2 PHYSICAL RECORD: n_fields 4; compact format; info bits 0
 0: len 4; hex 80000001; asc     ;;
 1: len 6; hex 0000000b87cd; asc       ;;
 2: len 7; hex 7a000001401e17; asc z   @  ;;
 3: len 4; hex 616c6578; asc alex;;

------------------
---TRANSACTION 755661, ACTIVE 48 sec
2 lock struct(s), heap size 1136, 1 row lock(s), undo log entries 1
MySQL thread id 54543, OS thread handle 139688378586880, query id 4535989 192.168.0.48 root cleaning up
--------
FILE I/O
--------
I/O thread 0 state: waiting for completed aio requests (insert buffer thread)
I/O thread 1 state: waiting for completed aio requests (log thread)
I/O thread 2 state: waiting for completed aio requests (read thread)
I/O thread 3 state: waiting for completed aio requests (read thread)
I/O thread 4 state: waiting for completed aio requests (read thread)
I/O thread 5 state: waiting for completed aio requests (read thread)
I/O thread 6 state: waiting for completed aio requests (write thread)
I/O thread 7 state: waiting for completed aio requests (write thread)
I/O thread 8 state: waiting for completed aio requests (write thread)
I/O thread 9 state: waiting for completed aio requests (write thread)
Pending normal aio reads: [0, 0, 0, 0] , aio writes: [0, 0, 0, 0] ,
 ibuf aio reads:, log i/o's:, sync i/o's:
Pending flushes (fsync) log: 0; buffer pool: 0
12203 OS file reads, 16269 OS file writes, 11550 OS fsyncs
0.00 reads/s, 0 avg bytes/read, 0.50 writes/s, 0.20 fsyncs/s
-------------------------------------
INSERT BUFFER AND ADAPTIVE HASH INDEX
-------------------------------------
Ibuf: size 1, free list len 0, seg size 2, 4 merges
merged operations:
 insert 4, delete mark 2, delete 2
discarded operations:
 insert 0, delete mark 0, delete 0
Hash table size 34673, node heap has 43 buffer(s)
Hash table size 34673, node heap has 3 buffer(s)
Hash table size 34673, node heap has 2 buffer(s)
Hash table size 34673, node heap has 7 buffer(s)
Hash table size 34673, node heap has 3 buffer(s)
Hash table size 34673, node heap has 1 buffer(s)
Hash table size 34673, node heap has 43 buffer(s)
Hash table size 34673, node heap has 2 buffer(s)
0.00 hash searches/s, 2.08 non-hash searches/s
---
LOG
---
Log sequence number 901543405
Log flushed up to   901543405
Pages flushed up to 901543405
Last checkpoint at  901543396
0 pending log flushes, 0 pending chkp writes
7354 log i/o's done, 0.12 log i/o's/second
----------------------
BUFFER POOL AND MEMORY
----------------------
Total large memory allocated 137428992
Dictionary memory allocated 2955229
Buffer pool size   8191
Free buffers       1024
Database pages     7063
Old database pages 2587
Modified db pages  0
Pending reads 0
Pending writes: LRU 0, flush list 0, single page 0
Pages made young 9840, not young 62859
0.00 youngs/s, 0.00 non-youngs/s
Pages read 12048, created 388, written 6940
0.00 reads/s, 0.00 creates/s, 0.37 writes/s
Buffer pool hit rate 1000 / 1000, young-making rate 0 / 1000 not 0 / 1000
Pages read ahead 0.00/s, evicted without access 0.00/s, Random read ahead 0.00/s
LRU len: 7063, unzip_LRU len: 0
I/O sum[22]:cur[0], unzip sum[0]:cur[0]
--------------
ROW OPERATIONS
--------------
0 queries inside InnoDB, 0 queries in queue
0 read views open inside InnoDB
Process ID=7329, Main thread ID=139688411121408, state: sleeping
Number of rows inserted 67953, updated 19732, deleted 20, read 881841486
0.12 inserts/s, 0.00 updates/s, 0.02 deletes/s, 0.52 reads/s
----------------------------
END OF INNODB MONITOR OUTPUT
============================
