package com.buleqr.utils;

import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.RedisTemplate; 
import org.springframework.stereotype.Component; 
 
import javax.annotation.Resource; 
import java.util.Arrays; 
import java.util.Collections; 
import java.util.List; 
import java.util.Map; 
import java.util.concurrent.TimeUnit; 
 
@Component 
// Redis操作工具类 
public class RedisOperatingUtil { 
    @Resource 
    private RedisTemplate<Object, Object> redisTemplate; 
 
    /** 
     * 指定 key 的过期时间 
     * 
     * @param key  键 
     * @param time 时间(秒) 
     */ 
    public void setKeyTime(String key, long time) { 
        redisTemplate.expire(key, time, TimeUnit.SECONDS); 
    } 
 
    /** 
     * 根据 key 获取过期时间（-1 即为永不过期） 
     * 
     * @param key 键 
     * @return 过期时间 
     */ 
    public Long getKeyTime(String key) { 
        return redisTemplate.getExpire(key, TimeUnit.SECONDS); 
    } 
 
    /** 
     * 判断 key 是否存在 
     * 
     * @param key 键 
     * @return 如果存在 key 则返回 true，否则返回 false 
     */ 
    public Boolean hasKey(String key) { 
        return redisTemplate.hasKey(key); 
    } 
 
    /** 
     * 删除 key 
     * 
     * @param key 键 
     */ 
    public Long delKey(String... key) { 
        if (key == null || key.length < 1) { 
            return 0L; 
        } 
        return redisTemplate.delete(Arrays.asList(key)); 
    } 
 
    /** 
     * 获取 Key 的类型 
     * 
     * @param key 键 
     */ 
    public String keyType(String key) { 
        DataType dataType = redisTemplate.type(key); 
        assert dataType != null; 
        return dataType.code(); 
    } 
 
    /** 
     * 批量设置值 
     * 
     * @param map 要插入的 key value 集合 
     */ 
    public void barchSet(Map<String, Object> map) { 
        redisTemplate.opsForValue().multiSet(map); 
    } 
 
    /** 
     * 批量获取值 
     * 
     * @param list 查询的 Key 列表 
     * @return value 列表 
     */ 
    public List<Object> batchGet(List<String> list) { 
        return redisTemplate.opsForValue().multiGet(Collections.singleton(list)); 
    } 
 
 
    /** 
     * 获取指定对象类型key的值 
     * 
     * @param key 键 
     * @return 值 
     */ 
    public Object objectGetKey(String key) { 
        return redisTemplate.opsForValue().get(key); 
    } 
 
    /** 
     * 设置对象类型的数据 
     * 
     * @param key   键 
     * @param value 值 
     */ 
    public void objectSetValue(String key, Object value) { 
        redisTemplate.opsForValue().set(key, value); 
    } 
 
    /** 
     * 向list的头部插入一条数据 
     * 
     * @param key   键 
     * @param value 值 
     */ 
    public Long listLeftPush(String key, Object value) { 
        return redisTemplate.opsForList().leftPush(key, value); 
    } 
 
    /** 
     * 向list的末尾插入一条数据 
     * 
     * @param key   键 
     * @param value 值 
     */ 
    public Long listRightPush(String key, Object value) { 
        return redisTemplate.opsForList().rightPush(key, value); 
    } 
 
    /** 
     * 向list头部添加list数据 
     * 
     * @param key   键 
     * @param value 值 
     */ 
    public Long listLeftPushAll(String key, List<Object> value) { 
        return redisTemplate.opsForList().leftPushAll(key, value); 
    } 
 
    /** 
     * 向list末尾添加list数据 
     * 
     * @param key   键 
     * @param value 值 
     */ 
    public Long listRightPushAll(String key, List<Object> value) { 
        return redisTemplate.opsForList().rightPushAll(key, value); 
    } 
 
    /** 
     * 通过索引设置list元素的值 
     * 
     * @param key   键 
     * @param index 索引 
     * @param value 值 
     */ 
    public void listIndexSet(String key, long index, Object value) { 
        redisTemplate.opsForList().set(key, index, value); 
    } 
 
    /** 
     * 获取列表指定范围内的list元素，正数则表示正向查找，负数则倒叙查找 
     * 
     * @param key   键 
     * @param start 开始 
     * @param end   结束 
     * @return boolean 
     */ 
    public Object listRange(String key, long start, long end) { 
        return redisTemplate.opsForList().range(key, start, end); 
    } 
 
    /** 
     * 从列表前端开始取出数据 
     * 
     * @param key 键 
     * @return 结果数组对象 
     */ 
    public Object listPopLeftKey(String key) { 
        return redisTemplate.opsForList().leftPop(key); 
    } 
 
    /** 
     * 从列表末尾开始遍历取出数据 
     * 
     * @param key 键 
     * @return 结果数组 
     */ 
    public Object listPopRightKey(String key) { 
        return redisTemplate.opsForList().rightPop(key); 
    } 
 
    /** 
     * 获取list长度 
     * 
     * @param key 键 
     * @return 列表长度 
     */ 
    public Long listLen(String key) { 
        return redisTemplate.opsForList().size(key); 
    } 
 
    /** 
     * 通过索引获取list中的元素 
     * 
     * @param key   键 
     * @param index 索引（index>=0时，0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推） 
     * @return 列表中的元素 
     */ 
    public Object listIndex(String key, long index) { 
        return redisTemplate.opsForList().index(key, index); 
    } 
 
    /** 
     * 移除list元素 
     * 
     * @param key   键 
     * @param count 移除数量（"负数"则从列表倒叙查找删除 count 个对应的值; "整数"则从列表正序查找删除 count 个对应的值;） 
     * @param value 值 
     * @return 成功移除的个数 
     */ 
    public Long listRem(String key, long count, Object value) { 
        return redisTemplate.opsForList().remove(key, count, value); 
    } 
 
    /** 
     * 截取指定范围内的数据, 移除不是范围内的数据 
     * @param key 操作的key 
     * @param start 截取开始位置 
     * @param end 截取激素位置 
     */ 
    public void listTrim(String key, long start, long end) { 
        redisTemplate.opsForList().trim(key, start, end); 
    } 
} 