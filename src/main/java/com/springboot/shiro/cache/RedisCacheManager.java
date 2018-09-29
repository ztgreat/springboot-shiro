package com.springboot.shiro.cache;

import org.apache.shiro.cache.AbstractCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

/**
 * shiro redis 缓存管理器
 */
public class RedisCacheManager extends AbstractCacheManager {

    @Override
    protected Cache createCache(String name) throws CacheException {
        return new RedisShiroCache<String,Object>(name);
    }
}
