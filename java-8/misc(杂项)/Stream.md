###串行流 [将大任务拆分，然后所有拆分后的任务，串行执行,最后将结果合并]


###并行流 [将大任务拆分，然后所有拆分后的任务，并行执行,最后将结果合并]
parallelStream默认使用了fork-join框架，其默认线程数是CPU核心数。
可以修改Stream默认线程的数量：
在运行代码之前，加入如下代码：  
```
System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "20");
```

```
 @Test
    public void test9() {
        //串行流
        boolean parallel1 = Stream.of(1, 2, 3)
                .isParallel();
        System.out.println(parallel1); //返回false

        //并行流
        boolean parallel2 = Stream.of(1, 2, 3)
                .parallel()
                .isParallel();
        System.out.println(parallel2); //返回true 
    }
```
