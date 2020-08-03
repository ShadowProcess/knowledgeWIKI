/**
 * 需要哪个模块，就导入哪个模块
 *
 * 当前模块导出你指定的包；其它模块需要就可以引入
 */
module java9 {
    //引入你需要的模块
    requires junit;
    requires java.logging;
    requires multijar;
    requires jdk.incubator.httpclient;

    //导出你指定的包，别的模块可以引入
    //exports com.review_java8;
}