package com.springboot.shiro.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionFactory;

public class ShiroSessionFactory implements SessionFactory {  
  
    @Override  
    public Session createSession(SessionContext initData) {  
        String host = initData.getHost();
        if(host != null)
            return new ShiroSession(host);
        return new ShiroSession();  
    }  
}  
