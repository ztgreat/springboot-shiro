package com.springboot.shiro.security;


import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * @author ztgreat
 * jackson在序列化的时候，先利用反射找到对象类的所有get方法，接下来去get，然后小写化，作为json的每个key值，
 * 而get方法的返回值作为value。接下来再反射field，添加到json中
 *
 * 在使用jackson 序列化时,因为shiro session 内部使用了一下私有的set集合,这样在反序列化的时候就会出错
 * shiro session 中缺少部分setter 方法
 */
public class RedisSimpleAuthenticationInfo extends SimpleAuthenticationInfo {

    public RedisSimpleAuthenticationInfo() {
    }

    public RedisSimpleAuthenticationInfo(Object principal, Object credentials, String realmName) {
        super.setCredentials(credentials);
        super.setPrincipals(new RedisSimplePrincipalCollection(principal,realmName));
    }

    public RedisSimpleAuthenticationInfo(Object principal, Object hashedCredentials, ByteSource credentialsSalt, String realmName) {
        super.setCredentials(hashedCredentials);
        super.setCredentialsSalt(credentialsSalt);
        super.setPrincipals(new RedisSimplePrincipalCollection(principal,realmName));
    }

    public RedisSimpleAuthenticationInfo(PrincipalCollection principals, Object credentials) {
        super.setCredentials(credentials);
        super.setPrincipals(new RedisSimplePrincipalCollection(principals));
    }

    public RedisSimpleAuthenticationInfo(PrincipalCollection principals, Object hashedCredentials, ByteSource credentialsSalt) {
        super.setCredentials(hashedCredentials);
        super.setCredentialsSalt(credentialsSalt);
        super.setPrincipals(new RedisSimplePrincipalCollection(principals));
    }
}
