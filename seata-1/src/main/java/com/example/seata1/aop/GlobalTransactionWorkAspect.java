package com.example.seata1.aop;

import io.seata.common.util.StringUtils;
import io.seata.core.context.RootContext;
import io.seata.core.exception.TransactionException;
import io.seata.tm.api.GlobalTransaction;
import io.seata.tm.api.GlobalTransactionContext;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;


/**
 * 处理分布式事务发起者，和被调用者，使用全局异常处理器时，导致Seata分布式事务失效问题
 * 处理全局异常导致分布式事务失效问题哦
 */
@Slf4j
@Aspect
@Component
public class GlobalTransactionWorkAspect {

    @Pointcut(value = "@annotation(global)")
    public void processing(Global global) {
    }


    /**
     * 方法执行之前手动开启分布式事务
     *
     * @param joinPoint
     * @param global
     * @throws TransactionException
     */
    @Before(value = "processing(global)", argNames = "joinPoint,global")
    public void before(JoinPoint joinPoint, Global global) throws TransactionException {
        int timeout = global.timeoutMills();
        String name = global.name();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        log.info("PointCut Method:{}", method);
        GlobalTransaction tx = GlobalTransactionContext.getCurrentOrCreate();
        tx.begin(timeout, name);
        log.info("*****创建分布式事务完毕*****{}", tx.getXid());
    }


    /**
     * 方法正常返回，如果下游被调用者使用全局异常拦截，如果不处理，将导致分布式事务失效
     *
     * @param joinPoint
     * @param result
     * @param global
     * @throws TransactionException
     */
    @AfterReturning(returning = "result", pointcut = "processing(global)", argNames = "joinPoint,result,global")
    public void doAfterReturning(JoinPoint joinPoint, Object result, Global global) throws TransactionException {
        log.info("切点:{}", joinPoint);
        log.info("切点执行结果:{}", result);
        String xid = RootContext.getXID();

        //如果分布式事务上下文XID存在，并且下游接口返回结果是success(代表下游服务执行成功)，那么分布式事务提交。
        if (StringUtils.isNotBlank(xid) && Objects.equals(result, "success")) {
            log.info("***分布式事务：{} 提交***", xid);
            GlobalTransactionContext.reload(xid).commit();
            log.info("***分布式事务提交完毕***");
            return;
        }

        //其它情况,回滚分布式事务
        if (StringUtils.isNotBlank(xid)) {
            log.info("***分布式事务：{} 回滚***", xid);
            GlobalTransactionContext.reload(xid).rollback();
            log.info("***分布式事务回滚完毕***");
        }
    }

    /**
     * 方法执行异常，手动回滚分布式事务
     *
     * @param e
     * @param global
     * @throws TransactionException
     */
    @AfterThrowing(throwing = "e", pointcut = "processing(global)", argNames = "e,global")
    public void doRecovery(Throwable e, Global global) throws TransactionException {
        log.info("切点方法执行异常:{}", e.getMessage());
        String xid = RootContext.getXID();
        if (StringUtils.isNotBlank(xid)) {
            log.info("***分布式事务：{} 回滚***", xid);
            GlobalTransactionContext.reload(xid).rollback();
            log.info("***分布式事务回滚完毕***");
        }
    }
}