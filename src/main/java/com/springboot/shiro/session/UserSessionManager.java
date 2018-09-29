package com.springboot.shiro.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class UserSessionManager {


	@Autowired
	RedisShiroSessionDao userShiroSessionDao;

	/**
	 * 根据ID查询 SimplePrincipalCollection
	 * @param userIds	用户ID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SimplePrincipalCollection> getSimplePrincipalCollectionByUserId(Long...userIds){

		return null;
	}
	
	
	
	/**
	 * 获取单个Session
	 * @param sessionId
	 * @return
	 */
	public Session getSession(String sessionId) {
		Session session = userShiroSessionDao.doReadSession(sessionId);
		return session;
	}

	/**
	 * 改变Session状态
	 * @param status {true:踢出,false:激活}
	 * @param sessionId
	 * @return
	 */
	public Map<String, Object> changeSessionStatus(Boolean status,
			String sessionId) {

		return null;
	}
	/**
	 * 查询要禁用的用户是否在线。
	 * @param id		用户ID
	 * @param status	用户状态
	 */
	public void forbidUserById(Long id, Long status) {
		
	}

}
