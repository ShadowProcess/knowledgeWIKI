

```java
public class Principle{

public ConfigurableApplicationContext run(String... args) {
//启动一个监视器
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		ConfigurableApplicationContext context = null;
		Collection<SpringBootExceptionReporter> exceptionReporters = new ArrayList<>();
//java.awt.headless是J2SE的一种模式用于在缺少显示屏、键盘或者鼠标时的系统配置，很多监控工具如jConsole 需要将该值设置为true，系统变量默认为true
		configureHeadlessProperty();
//第一步：获取并启动监听器
		SpringApplicationRunListeners listeners = getRunListeners(args);
		listeners.starting();
		try {
			ApplicationArguments applicationArguments = new DefaultApplicationArguments(args);
//第二步：构造容器环境
			ConfigurableEnvironment environment = prepareEnvironment(listeners, applicationArguments);
            //设置需要忽略的bean
			configureIgnoreBeanInfo(environment);
            //打印Banner
			Banner printedBanner = printBanner(environment);
//第三步：创建容器
			context = createApplicationContext();
//第四步：实例化SpringBootExceptionReporter.class，用来支持报告关于启动的错误
			exceptionReporters = getSpringFactoriesInstances(SpringBootExceptionReporter.class,
					new Class[] { ConfigurableApplicationContext.class }, context);
//第五步：准备容器
			prepareContext(context, environment, listeners, applicationArguments, printedBanner);
//第六步：刷新容器
			refreshContext(context);
//第七步：刷新容器后的扩展接口
			afterRefresh(context, applicationArguments);
			stopWatch.stop();
			if (this.logStartupInfo) {
				new StartupInfoLogger(this.mainApplicationClass).logStarted(getApplicationLog(), stopWatch);
			}
			listeners.started(context);
			callRunners(context, applicationArguments);
		}
		catch (Throwable ex) {
			handleRunFailure(context, ex, exceptionReporters, listeners);
			throw new IllegalStateException(ex);
		}

		try {
			listeners.running(context);
		}
		catch (Throwable ex) {
			handleRunFailure(context, ex, exceptionReporters, null);
			throw new IllegalStateException(ex);
		}
		return context;
	}
}
```