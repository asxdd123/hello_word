package com.hehe.springbooredis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Author: 就不告诉你
 * @CreateTime: 2022-11-04 00:21
 * redis 序列化配置
 */
@Configuration
public class RedisConfig {
    /**
     * redis序列化配置
     * @param connectionFactory 连接工厂
     * @return
     */
    @Bean
    public RedisTemplate redisTemplate(LettuceConnectionFactory connectionFactory) {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(connectionFactory);
        // 替换默认序列化
        GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
//        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);   //反序列value
        redisTemplate.setValueSerializer(new StringRedisSerializer());  //解决append报错问题测试
        redisTemplate.setKeySerializer(new StringRedisSerializer());      //反序列key
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());    //反序列hashKey
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);  //反序列hashValue
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}

