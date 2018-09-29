package com.springboot.shiro.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;

import java.io.Serializable;

public class RedisShiroSessionDao extends CachingSessionDAO {

	private String cacheName="RedisShiroSessionCache";

	@Override
	protected void doUpdate(Session session) {
		this.getCacheManager().getCache(cacheName).put(session.getId(),session);
	}

	@Override
	protected void doDelete(Session session) {
		this.getCacheManager().getCache(cacheName).remove(session.getId());
	}

	@Override
	protected Serializable doCreate(Session session) {
		this.getCacheManager().getCache(cacheName).put(session.getId(),session);
		return session.getId();
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		return (Session) this.getCacheManager().getCache(cacheName).get(sessionId);
	}
}
