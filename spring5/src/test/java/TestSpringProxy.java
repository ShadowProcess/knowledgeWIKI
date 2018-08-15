import com.example.basic.spring_proxy.AService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class TestSpringProxy {

    @Test
    public void test1() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext4.xml");
        AService c = (AService) ctx.getBean("a");
        c.show();
    }

}
