package com.springboot.shiro.configure;

import com.springboot.shiro.cache.RedisCacheManager;
import com.springboot.shiro.filter.LoginFilter;
import com.springboot.shiro.filter.PermissionFilter;
import com.springboot.shiro.filter.RoleFilter;
import com.springboot.shiro.security.MyPermissionResolver;
import com.springboot.shiro.security.SysUserRealm;
import com.springboot.shiro.session.RedisShiroSessionDao;
import com.springboot.shiro.session.ShiroSessionFactory;
import com.springboot.shiro.session.UserSessionManager;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionFactory;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "spring.shiro")
public class ShiroConfig {

    //session 过期时间
    private Long globalSessionTimeout;

    private String cookieName;


    public Long getGlobalSessionTimeout() {
        return globalSessionTimeout;
    }

    public void setGlobalSessionTimeout(Long globalSessionTimeout) {
        this.globalSessionTimeout = globalSessionTimeout;
    }

    public String getCookieName() {
        return cookieName;
    }

    public void setCookieName(String cookieName) {
        this.cookieName = cookieName;
    }

    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        Map<String,Filter> filters = new HashMap<>();

        filters.put("role",roleFilter());
        filters.put("permission",permissionFilter());
        filters.put("login",loginFilter());


        shiroFilterFactoryBean.setFilters(filters);

        //拦截器.
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
        // 配置不会被拦截的链接 顺序判断
        filterChainDefinitionMap.put("/api/login/**", "anon");
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/api/logout", "login");
        //<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterChainDefinitionMap.put("/**", "login,permission");
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/");

        //未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/login");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 角色过滤器
     * @return
     */
    public AccessControlFilter roleFilter(){
        RoleFilter roleFilter = new RoleFilter();
        return roleFilter;
    }

    /**
     * 登录过滤器
     * @return
     */
    public AccessControlFilter loginFilter(){
        LoginFilter loginFilter = new LoginFilter();
        return loginFilter;
    }

    /**
     * 资源过滤器
     * @return
     */
    public AccessControlFilter permissionFilter(){
        PermissionFilter permissionFilter = new PermissionFilter();
        return permissionFilter;
    }


    /**
     * 会话验证调度器
     * @param sessionManager
     * @return
     */
//    @Bean
//    public SessionValidationScheduler sessionValidationScheduler(DefaultWebSessionManager sessionManager){
//        ExecutorServiceSessionValidationScheduler sessionValidationScheduler= new ExecutorServiceSessionValidationScheduler();
//
//        //注入sessionManager
//        sessionValidationScheduler.setSessionManager(sessionManager);
//        return sessionValidationScheduler;
//    }



    @Bean
    public SessionFactory sessionFactory(){
        return new ShiroSessionFactory();
    }

    /**
     * 缓存管理器
     * @return
     */

    @Bean
    public DefaultWebSessionManager sessionManager(RedisShiroSessionDao redisShiroSessionDao,SessionFactory sessionFactory,Cookie cookie ){
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();

        //注入 sessionDao
        sessionManager.setSessionDAO(redisShiroSessionDao);

        //注入 会话验证调度器
        //sessionManager.setSessionValidationScheduler(sessionValidationScheduler);

        //注入 sessionFactory
        sessionManager.setSessionFactory(sessionFactory);

        //设置session 过期时间
        if(this.globalSessionTimeout !=null){
            sessionManager.setGlobalSessionTimeout(this.globalSessionTimeout);
        }

        //设置cookie 模板
        sessionManager.setSessionIdCookie(cookie);

        return sessionManager;
    }

    @Bean
    public Cookie cookie(){
        SimpleCookie cookie = new SimpleCookie();
        cookie.setHttpOnly(true);
        cookie.setMaxAge(-1);
        if(this.cookieName==null){
            cookie.setName("default_manager");
        }else {
            cookie.setName(cookieName);
        }
        return cookie;
    }

    @Bean
    public SessionIdGenerator sessionIdGenerator(){
        return new JavaUuidSessionIdGenerator();
    }

    public PermissionResolver permissionResolver(){
        PermissionResolver permissionResolver = new MyPermissionResolver();
        return permissionResolver;
    }


    /**
     * SessionDao
     * @param sessionIdGenerator
     * @return
     */
    @Bean
    public RedisShiroSessionDao redisShiroSessionDao(SessionIdGenerator sessionIdGenerator){
        RedisShiroSessionDao redisShiroSessionDao = new RedisShiroSessionDao();

        //注入id 生成器
        redisShiroSessionDao.setSessionIdGenerator(sessionIdGenerator);
        return redisShiroSessionDao;
    }


    /**
     * realm
     * @return
     */
    @Bean
    public SysUserRealm sysUserRealm(){
        SysUserRealm sysUserRealm = new SysUserRealm();

        //注入匹配器
        sysUserRealm.setPermissionResolver(permissionResolver());

        //关闭缓存
        sysUserRealm.setCachingEnabled(false);
        sysUserRealm.setAuthenticationCachingEnabled(false);
        sysUserRealm.setAuthorizationCachingEnabled(false);
        return sysUserRealm;
    }


    /**
     * 安全管理器
     * @return
     */
    @Bean
    public SecurityManager securityManager(SessionManager sessionManager, Realm sysUserRealm,CacheManager cacheManager){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        //注入realm
        securityManager.setRealm(sysUserRealm);
        //注入 sessionManager
        securityManager.setSessionManager(sessionManager);

        //注入cacheManager
        securityManager.setCacheManager(cacheManager);
        return securityManager;
    }


    /**
     * 缓存管理器
     * @return
     */
    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate){
        RedisCacheManager cacheManager = new RedisCacheManager();
        cacheManager.setRedisTemplate(redisTemplate);
        return cacheManager;
    }

    @Bean
    public UserSessionManager userSessionManager(RedisShiroSessionDao redisShiroSessionDao){
        UserSessionManager userSessionManager = new UserSessionManager();
        userSessionManager.setRedisShiroSessionDao(redisShiroSessionDao);
        return userSessionManager;
    }


}
