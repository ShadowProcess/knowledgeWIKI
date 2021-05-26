```java
@Controller
public class A {

@GetMapping("/checkStateTimeoutFallback/{userId}")
@HystrixCommand(
            // 线程池标识，要保持唯一，不唯一的话就共用了
            threadPoolKey = "findResumeOpenStateTimeoutFallback",
            // 线程池细节属性配置
            threadPoolProperties = {
                    @HystrixProperty(name="coreSize",value = "2"), // 线程数
                    @HystrixProperty(name="maxQueueSize",value="20") // 等待队列长度
            },
            // commandProperties熔断的一些细节属性配置
            commandProperties = {
                    // 每一个属性都是一个HystrixProperty
                    @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="2000"),

                    // hystrix高级配置，定制工作过程细节
                    
                    // 统计时间窗口定义
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds",value = "8000"),
                    // 统计时间窗口内的最小请求数
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "2"),
                    // 统计时间窗口内的错误数量百分比阈值
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
                    // 自我修复时的活动窗口长度
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "3000")
            },
            fallbackMethod = "myFallBack"  // 回退方法
    )
    public Integer findResumeOpenStateTimeoutFallback(@PathVariable Long userId) {
        // 使用ribbon不需要我们自己获取服务实例然后选择一个那么去访问了（自己的负载均衡）
        String url = "http://lagou-service-resume/resume/openstate/" + userId;  // 指定服务名
        Integer forObject = restTemplate.getForObject(url, Integer.class);
        return forObject;
    }
}
```

```
public @interface HystrixCommand {

            // HystrixCommand 命令所属的组的名称：默认注解方法类的名称
            String groupKey() default "";

            // HystrixCommand 命令的key值，默认值为注解方法的名称
            String commandKey() default "";

            // 线程池名称，默认定义为groupKey
            String threadPoolKey() default "";

            // 定义回退方法的名称, 此方法必须和hystrix的执行方法在相同类中
            String fallbackMethod() default "";

            // 配置hystrix命令的参数
            HystrixProperty[] commandProperties() default {};

            // 配置hystrix依赖的线程池的参数
             HystrixProperty[] threadPoolProperties() default {};

            // 如果hystrix方法抛出的异常包括RUNTIME_EXCEPTION，则会被封装HystrixRuntimeException异常。我们也可以通过此方法定义哪些需要忽略的异常
            Class<? extends Throwable>[] ignoreExceptions() default {};

            // 定义执行hystrix observable的命令的模式，类型详细见ObservableExecutionMode
            ObservableExecutionMode observableExecutionMode() default ObservableExecutionMode.EAGER;

            // 如果hystrix方法抛出的异常包括RUNTIME_EXCEPTION，则会被封装HystrixRuntimeException异常。此方法定义需要抛出的异常
            HystrixException[] raiseHystrixExceptions() default {};

            // 定义回调方法：但是defaultFallback不能传入参数，返回参数和hystrix的命令兼容
            String defaultFallback() default "";
        }
```


总结：
command group一般来说，可以是对应一个服务，多个command key对应这个服务的多个接口，多个接口的调用共享同一个线程池

如果说你的command key，要用自己的线程池，可以定义自己的threadPoolKey，就ok了

```
实际测试：
配置方式：@HystrixCommand(fallbackMethod = "helloFallback", groupKey = "Group1", threadPoolKey = "HystrixFooServiceGaGa")

1.其中commandKey目前还没有发现有什么用，下面重点看groupKey 和 threadPoolKey

当上面3个属性都不配置的时候，比如工程中的一个HystrixUserService 这个类中所有标明了@HystrixCommand标的方法，
都是使用1个线程池（线程名用的是类名hystrix-HystrixUserService-1 ），而如果有HystrixUserService2，
里面也有@HystrixCommand标的方法，那么执行的时候使用的是另一个线程池，所以默认就已经是线程池隔离的

2.如果同时配置了groupKey 和 threadPoolKey ，那么具有相同的threadPoolKey的使用同一个线程池；

3.如果只配置了groupKey ，那么具有相同的groupKey 的使用同一个线程池；
比如如果配置的是@HystrixCommand(fallbackMethod = "helloFallback", groupKey = "Group1")，那么线程名就是hystrix-Group1-*

总结：通常情况下，尽量通过 HystrixThreadPoolKey 的方式来指定线程池的划分， 而不是通过组名的默认方式实现划分， 
因为多个不同的命令可能 从业务逻辑上来看属于同 一个组， 但是往往从实现本身上需要跟其他命令进行隔离，
除非这个业务的各个接口调用频率都差不多，没有特别大的区别，那么就不需要单独设置ThreadPoolKey，直接用groupkey就可以了。
```
