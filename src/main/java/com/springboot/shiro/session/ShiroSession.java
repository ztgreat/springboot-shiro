package com.springboot.shiro.session;

import org.apache.shiro.session.mgt.SimpleSession;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public class ShiroSession extends SimpleSession implements Serializable {

	private static final long serialVersionUID = 1L;
	
    public ShiroSession() {
        super();  
    }
  
    public ShiroSession(String host) {  
        super(host);  
    }
    public void setAttributeKeys(Map<Object, Object> attributeKeys) {
        super.setAttributes(attributeKeys);
    }
}