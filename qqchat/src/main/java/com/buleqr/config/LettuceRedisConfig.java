package com.buleqr.config;
 
import org.springframework.context.annotation.Bean; 
import org.springframework.context.annotation.Configuration; 
import org.springframework.data.redis.connection.RedisConnectionFactory; 
import org.springframework.data.redis.core.RedisTemplate; 
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer; 
import org.springframework.data.redis.serializer.StringRedisSerializer; 
 
 
// 自定义RedisTemplate设置序列化器, 方便转换redis中的数据与实体类互转 
@Configuration 
public class LettuceRedisConfig { 
    /** 
     * Redis 序列化配置 
     */ 
    @Bean 
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) { 
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>(); 
        redisTemplate.setConnectionFactory(connectionFactory); 
        // 使用GenericJackson2JsonRedisSerializer替换默认序列化 
        GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer(); 
        // 设置 Key 和 Value 的序列化规则 
        redisTemplate.setKeySerializer(new StringRedisSerializer()); 
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer); 
        redisTemplate.setHashKeySerializer(new StringRedisSerializer()); 
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer); 
        // 初始化 RedisTemplate 序列化完成 
        redisTemplate.afterPropertiesSet(); 
        return redisTemplate; 
    } 
} 