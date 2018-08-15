import com.example.basic.static_proxy.*;
import org.junit.Test;


/**
 * 测试静态代理
 */
public class TestStaticProxy {

    /**
     * 即享受了核心功能，又享受了额外功能
     */
    @Test
    public void test1() {
        UserService userService = new UserServiceProxy();
        userService.login("sun","1233");
        userService.register(new User());
        System.out.println("------------------");
        OrderService orderService = new OrderServiceProxy();
        orderService.showOrder();
    }

}
