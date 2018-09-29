package com.springboot.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.Set;

/**
 * 使用redis 作为缓存器 
 */
public class RedisShiroCache<K,V> implements Cache<K, V> {

    @Autowired
    private RedisTemplate<K, V> redisTemplate;

    private String name;

    public RedisShiroCache(){
    }

    public RedisShiroCache(String name){
        this.name =name;
    }

    @Override
    public V get(K k) throws CacheException {
        return redisTemplate.opsForValue().get(k);
    }

    @Override
    public V put(K k, V v) throws CacheException {
        redisTemplate.opsForValue().set(k,v);
        return v;
    }

    @Override
    public V remove(K k) throws CacheException {
        V v=redisTemplate.opsForValue().get(k);
        redisTemplate.delete(k);
        return v;
    }

    @Override
    public void clear() throws CacheException {
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<K> keys() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }
}
