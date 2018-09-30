package com.springboot.shiro.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;

import java.util.Collection;
import java.util.Set;

/**
 * @author ztgreat
 * jackson在序列化的时候，先利用反射找到对象类的所有get方法，接下来去get，然后小写化，作为json的每个key值，
 * 而get方法的返回值作为value。接下来再反射field，添加到json中
 *
 * 在使用jackson 序列化时,因为shiro session 内部使用了一下私有的set集合,这样在反序列化的时候就会出错
 * shiro session 中缺少部分setter 方法
 */

public class RedisSimplePrincipalCollection extends SimplePrincipalCollection {


    public RedisSimplePrincipalCollection() {
    }

    public RedisSimplePrincipalCollection(Object principal, String realmName) {
        super(principal, realmName);
    }

    public RedisSimplePrincipalCollection(Collection principals, String realmName) {
        super(principals, realmName);
    }

    public RedisSimplePrincipalCollection(PrincipalCollection principals) {
        super(principals);
    }

    /**
     * 在redis 序列化中忽略
     */
    @Override
    @JsonIgnore
    public Set<String> getRealmNames() {
        return super.getRealmNames();
    }

}
