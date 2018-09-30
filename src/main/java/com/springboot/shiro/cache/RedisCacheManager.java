package com.springboot.shiro.cache;

import org.apache.shiro.cache.AbstractCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * shiro redis 缓存管理器
 */
public class RedisCacheManager extends AbstractCacheManager {

    private RedisTemplate redisTemplate;


    @Override
    protected Cache createCache(String name) throws CacheException {
        RedisShiroCache redisShiroCache = new RedisShiroCache<String,Object>(name,redisTemplate);
        return redisShiroCache;
    }

    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

}
