import com.example.basic.dynamic_proxy.PrimitiveDynamicProxy;
import org.junit.Test;


/**
 * 测试java原生动态代理
 */
public class TestDynamicProxy {

    /**
     * 即享受了核心功能，又享受了额外功能
     */
    @Test
    public void test1() {
        PrimitiveDynamicProxy p = new PrimitiveDynamicProxy();
        p.getDynamicProxy().out();
    }

}
