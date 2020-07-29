package com.example.executor;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/**
 *
 * 构造函数的参数说一下
 *
 * int corePoolSize   线程池规定线程数（不超载的情况下）
 * int maximumPoolSize  线程池最大线程数 （已经超载了，最大能力）
 * long keepAliveTimes  线程没事干时最长存活时间
 * int TimeUnit 就是上面这个参数keepAliveTimes  的单位（年、月、天、小时、分钟、秒、毫秒、微秒）
 * BlockingQueue bockingqueue  阻塞任务队列（超载情况下排队等待的任务数组）
 *
 *
 * 最关键的参数就是这么几个，最基本的任务分配策略也是基于这几个参数。下面是任务分配策略
 *
 * 1、线程池初始默认线程数为0，当有任务来临时，创建新线程执行任务
 * 2、当来的任务数大于线程池规定线程数（corePoolSize ）时，剩下多余的任务到 阻塞任务队列（bockingqueue ）中等待
 * 3、当阻塞任务队列也满了的时候，也就是排队也没地儿排队了的时候，线程池会新建线程来执行任务
 * 4、当线程池的线程数达到最大线程数（maximumPoolSize ）时，再来任务也接不了了，会按照拒绝任务策略来处理剩下的事情。
 *
 *
 *
 * keepAliveTimes  这个参数：当线程池中的线程数大于规定线程数（corePoolSize ）时，而且又没啥任务，这个时候，多余的线程在 keepAliveTimes  时间后就会消亡，线程池线程数量小于 规定线程数（corePoolSize ）
 *
 *
 *
 * 最后再说线程池的两个方法，shutdown() 和 shutdownNow()
 *
 * shutdown()：执行这个方法后，线程池不再接受新的任务，当执行完手里的任务（包括阻塞队列里的任务）后，线程池关闭【这个就相当于要离职了，也要处理好手里的事情】
 * shutdownNow()：执行这个方法后，线程池不再接受新的任务，并且会尝试中断手里的任务，然后线程池关闭【这个就是要离职的时候，马上什么都不干了，拍屁股走人】
 *
 */
public class TestSimpleDateFormat {

    /**
     * SimpleDateFormat的多线程安全问题
     */
    public static void main0(String[] args) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        Callable<Date> task = new Callable<Date>() {
            @Override
            public Date call() throws Exception {
                return sdf.parse("20161121");
            }
        };

        ExecutorService pool = Executors.newFixedThreadPool(10);

        List<Future<Date>> results = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            results.add(pool.submit(task));
        }

        for (Future<Date> future : results) {
            System.out.println(future.get());
        }

        pool.shutdown();
    }




    /**
     * 使用ThreadLocal
     * 解决SimpleDateFormat的多线程安全问题
     */
    public static void main(String[] args) throws Exception{
        //解决多线程安全问题
		Callable<Date> task = new Callable<Date>() {

			@Override
			public Date call() throws Exception {
				return DateFormatThreadLocal.convert("20161121");
			}

		};

		ExecutorService pool = Executors.newFixedThreadPool(10);

		List<Future<Date>> results = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			results.add(pool.submit(task));
		}

		for (Future<Date> future : results) {
			System.out.println(future.get());
		}

		pool.shutdown();
    }




    /**
     *  jdk1.8的 DateTimeFormatter 不存在线程安全问题
     */
    public static void main1(String[] args) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd"); //该类是final修饰的，不可变

        //1.创建任务
        Callable<LocalDate> task = new Callable<LocalDate>() {
            @Override
            public LocalDate call() throws Exception {
                LocalDate ld = LocalDate.parse("20161121", dtf);
                return ld;
            }
        };

        //2.创建线程池
        ExecutorService pool = Executors.newFixedThreadPool(10);

        List<Future<LocalDate>> results = new ArrayList<>();

        //3.把任务提交到线程池，并把返回的Future装进集合
        for (int i = 0; i < 10; i++) {
            results.add(pool.submit(task));
        }

        for (Future<LocalDate> future : results) {
            System.out.println(future.get());
        }

        pool.shutdown();
    }

}
