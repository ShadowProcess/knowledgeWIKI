package com.example.filewatch;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.*;
import java.util.logging.Logger;

/**
 * WatchService
 * 看作是文件监控器，通过操作系统原生文件系统来运行。
 * 针对单点多appkey的情况，可以注册开启多个监控器。
 * 每个监控器可看作是后台线程，通过监控文件发出的信号来实现监控。
 * <p>
 * 这种方式仅适合于比较小的项目，例如只有一两台服务器，而且配置文件是可以直接修改的。例如 Spring mvc 以 war 包的形式部署，
 * 可以直接修改resources 中的配置文件。如果是 Spring boot 项目，还想用这种方式的话，就要引用一个外部可以编辑的文件，
 * 比如一个固定的目录，因为 spring boot 大多数以 jar 包部署，打到包里的配置文件没办法直接修改。如果是比较大的项目，
 * 最好还是用配置中心，例如携程的 Apollo、Consul 等。
 */
public class WatchServiceTest {

    private static final Logger log = Logger.getLogger("log");

    private static WatchService watchService;

    public static void main(String[] args) throws IOException, URISyntaxException {
        log.info("1.启动配置文件监控器");
        watchService = FileSystems.getDefault().newWatchService();
        URL url = WatchServiceTest.class.getResource("/"); //获取当前类文件所在路径
        log.info("url是：" + "file:/D:/DevelopmentSoftWare/workstation/knowledgeWIKI/java-8/target/classes/");
        Path path = Paths.get(url.toURI());
        log.info("path是：" + "D:\\DevelopmentSoftWare\\workstation\\knowledgeWIKI\\java-8\\target\\classes");
        path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_CREATE); //在指定的Path上注册监视器


        log.info("2.启动文件监控线程");
        Thread watchThread = new Thread(new WatchThread());
        watchThread.setDaemon(true);
        watchThread.start();


        log.info("3.注册JVM关闭时的钩子");
        Thread hook = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    watchService.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Runtime.getRuntime().addShutdownHook(hook);
    }


    public static class WatchThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                //尝试获取监控池的变化，如果没有则一直等待
                try {
                    WatchKey watchKey = watchService.take();
                    for (WatchEvent<?> event : watchKey.pollEvents()) {
                        String editFileName = event.context().toString();
                        log.info("发生变化的文件是:" + editFileName);

                         //TODO 这里可以重新加载配置文件....
                    }
                    watchKey.reset(); //完成一次监控就需要重启监控一次
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


/**
 * 事件在 StandardWatchEventKinds 类中定义，共有四种：
 *
 * 1、StandardWatchEventKinds#OVERFLOW
 *
 * 2、StandardWatchEventKinds#ENTRY_CREATE
 *
 * 3、StandardWatchEventKinds#ENTRY_DELETE
 *
 * 4、StandardWatchEventKinds#ENTRY_MODIFY
 */

