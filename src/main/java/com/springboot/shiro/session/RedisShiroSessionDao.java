package com.springboot.shiro.session;

import com.springboot.shiro.cache.RedisShiroCache;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;

import java.io.Serializable;

public class RedisShiroSessionDao extends CachingSessionDAO {

	private String cacheName="RedisShiroSessionCache";



	@Override
	protected void cache(Session session, Serializable sessionId, Cache<Serializable, Session> cache) {

		if(cache instanceof RedisShiroCache){
			RedisShiroCache redisShiroCache = (RedisShiroCache) cache;
			// redis 里面多保存5分钟
			redisShiroCache.put(session.getId(),session,session.getTimeout()/1000+300);
		}else {
			cache.put(sessionId, session);
		}
	}

	@Override
	protected void doUpdate(Session session) {
		Cache cache= this.getCacheManager().getCache(cacheName);

		if(cache instanceof RedisShiroCache){
			RedisShiroCache redisShiroCache = (RedisShiroCache) cache;

			// redis 里面多保存5分钟
			redisShiroCache.put(session.getId(),session,session.getTimeout()/1000+300);
		}else{
			cache.put(session.getId(),session);
		}

	}

	@Override
	protected void doDelete(Session session) {
		this.getCacheManager().getCache(cacheName).remove(session.getId());
	}

	@Override
	protected Serializable doCreate(Session session) {

		Serializable sessionId = this.generateSessionId(session);
		this.assignSessionId(session, sessionId);

		Cache cache= this.getCacheManager().getCache(cacheName);

		if(cache instanceof RedisShiroCache){
			RedisShiroCache redisShiroCache = (RedisShiroCache) cache;
			// redis 里面多保存5分钟
			redisShiroCache.put(sessionId,session,session.getTimeout()/1000+300);
		}else{
			cache.put(sessionId,session);
		}
		return sessionId;
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		return (Session) this.getCacheManager().getCache(cacheName).get(sessionId);
	}
}
