package com.example.preconditions;

import com.google.common.base.Preconditions;
import org.junit.Test;

/**
 * 参数前置检查
 */

public class PreTest {

    @Test
    public void _0() {
        Preconditions.checkArgument(1 > 2, "Args %s ssss", 2);
        Preconditions.checkArgument(1 < 2, "Expected i < j, but %s > %s", 1, 2);

        Preconditions.checkNotNull(null,"不能为空");
    }
}
