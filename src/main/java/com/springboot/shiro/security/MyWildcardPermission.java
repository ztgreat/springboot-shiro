package com.springboot.shiro.security;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.WildcardPermission;

import java.util.List;
import java.util.Set;

public class MyWildcardPermission extends WildcardPermission{
	
	private static final long serialVersionUID = 1L;
	
    protected MyWildcardPermission() {
    }

    public MyWildcardPermission(String wildcardString) {
        super(wildcardString, DEFAULT_CASE_SENSITIVE);
    }

    public MyWildcardPermission(String wildcardString, boolean caseSensitive) {
    	super.setParts(wildcardString, caseSensitive);
    }

	@Override
	public boolean implies(Permission p) {
		
        if (!(p instanceof MyWildcardPermission)) {
            return false;
        }
        MyWildcardPermission wp = (MyWildcardPermission) p;

        List<Set<String>> otherParts = wp.getParts();

        int i = 0;
        for (Set<String> otherPart : otherParts) {
            // If this permission has less parts than the other permission, everything after the number of parts contained
            // in this permission is automatically implied, so return true
            if (getParts().size() - 1 < i) {
                return true;
            } else {
            	// part 相当于 是数据库中的内容，otherPart 相当于 是传入的参数
                Set<String> part = getParts().get(i);
                if (!part.contains(WILDCARD_TOKEN) && !containsAll(part, otherPart)) {
                    return false;
                }
                i++;
            }
        }

        // If this permission has more parts than the other parts, only imply it if all of the other parts are wildcards
        for (; i < getParts().size(); i++) {
            Set<String> part = getParts().get(i);
            if (!part.contains(WILDCARD_TOKEN)) {
                return false;
            }
        }

        return true;
	}
	@Override
	protected void setParts(List<Set<String>> parts) {
		super.setParts(parts);
	}
	@Override
	protected void setParts(String wildcardString, boolean caseSensitive) {
		super.setParts(wildcardString, caseSensitive);
	}

	/**
	 * 集合a 是否包含target
	 * @param a
	 * @param target
	 * @return
	 */
	private boolean contains(Set<String> a,String target){
		
		for(String s: a){
			// 末尾含通配
			if (s.endsWith(WILDCARD_TOKEN)) {
				String temp=s.substring(0, s.length()-1);
				if (target.startsWith(temp)) {
					return true;
				}
			}else if(target.startsWith(s)){
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * 集合a 是否全部包含集合b
	 * @param a
	 * @param b
	 * @return
	 */
	private boolean containsAll(Set<String> a,Set<String> b){
		
		for(String s: b){
			if (!contains(a, s)) {
				return false;
			}
		}
		return true;
	}

}
