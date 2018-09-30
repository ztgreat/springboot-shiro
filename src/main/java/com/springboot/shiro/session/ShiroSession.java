package com.springboot.shiro.session;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.mgt.SimpleSession;

import java.io.Serializable;
import java.util.*;

/**
 * 解决 jackson 序列化 SimpleSession 是出错
 */
public class ShiroSession extends SimpleSession implements Serializable {

	private static final long serialVersionUID = 1L;
	
    public ShiroSession() {
        super();  
    }
  
    public ShiroSession(String host) {  
        super(host);  
    }

    /**
     * 仅仅用于反序列化
     * @param map
     * @return
     */
    public Map<Object, Object> setAttributesLazy(Map<Object, Object> map){
        return null;
    }

    /**
     * 序列化忽略
     * @return
     * @throws InvalidSessionException
     */
    @Override
    @JsonIgnore
    public Collection<Object> getAttributeKeys() throws InvalidSessionException {
        return super.getAttributeKeys();
    }
}