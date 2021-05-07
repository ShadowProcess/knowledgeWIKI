//package com.example.apigateway.service;
//
//import com.asia.gate.entity.Limit;
//import com.asia.gate.util.JsonUtils;
//import com.asia.gate.util.RedisUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//import java.util.Map;
//
//@Slf4j
//@Service
//public class LimitServiceImpl implements LimitService {
//
//    @Override
//    public boolean limit(RedisUtil redisUtil, Map<Object, Object> objectMap, String accessKeyId, String uuid, String account) {
//        String key = "openapi:" + accessKeyId + ":limit";
//        Map<Object, Object> limitMap = redisUtil.hgetMore(key);
//        Map<Object, Object> customMap = redisUtil.hgetMore("custom:" + account);
//        boolean isMax = true;
//        int requestRates = Integer.parseInt(customMap.get("requestRates").toString());
//        int numberMax = Integer.parseInt(objectMap.get("keyNumberMax").toString());
//        if (limitMap != null && limitMap.size() > 0) {
//            Limit limitObj = JsonUtils.toJavaObject(limitMap, Limit.class);
//            long frequency = limitObj.getFrequency();
//            long limit = limitObj.getLimit();
//            long timstamp = limitObj.getTimstamp();
//            long timeMillis = System.currentTimeMillis();
//            frequency++;
//            if (requestRates != limit) {
//                frequency = 1;
//                Limit limit1 = new Limit();
//                limit1.setLimit(requestRates);
//                limit1.setFrequency(frequency);
//                limit1.setTimstamp(timeMillis);
//                redisUtil.setRemove(key);
//                redisUtil.hsetMore(key, JsonUtils.toMap(limit1), requestRates * 60);
//            } else {
//                if (frequency > numberMax) {
//                    log.info("{},请求次数达到上限", uuid);
//                    isMax = false;
//                }
//                Limit limit1 = new Limit();
//                limit1.setLimit(requestRates);
//                limit1.setFrequency(frequency);
//                limit1.setTimstamp(timstamp);
//                long sec = (limit * 60) - ((timeMillis / 1000 - timstamp / 1000));
//                if (sec <= 0L) {
//                    limit1.setFrequency(1);
//                    redisUtil.hsetMore(key, JsonUtils.toMap(limit1), limit * 60);
//                    log.info("{},redis信息过期，所以将这次请求并入下一次周期的限流", uuid);
//                    isMax = true;
//                } else
//                    redisUtil.hsetMore(key, JsonUtils.toMap(limit1), sec);
//            }
//        } else {
//            log.info("{},key:{},第一次请求，初始化数据", uuid, accessKeyId);
//            Limit limit1 = new Limit();
//            limit1.setLimit(requestRates);
//            limit1.setFrequency(1);
//            limit1.setTimstamp(System.currentTimeMillis());
//            redisUtil.hsetMore(key, JsonUtils.toMap(limit1), requestRates * 60);
//        }
//        return isMax;
//    }
//}
