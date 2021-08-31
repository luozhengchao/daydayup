package com.space.kylin.redis;

import io.lettuce.core.RedisClient;

/**
 * @author luozhengchao
 * @date 2021/7/17 7:26 下午
 */
public class RedisTest {

    public static void main(String[] args) {
        RedisClient redisClient = RedisClient.create("localhost 6379");
        redisClient.connect();
    }
}
