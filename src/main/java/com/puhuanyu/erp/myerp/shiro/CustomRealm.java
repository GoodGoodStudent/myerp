package com.puhuanyu.erp.myerp.shiro;

import com.alibaba.fastjson.JSON;
import com.puhuanyu.erp.myerp.bean.Emp;
import com.puhuanyu.erp.myerp.bean.Ranks;
import com.puhuanyu.erp.myerp.service.EmpService;
import com.puhuanyu.erp.myerp.service.RanksService;
import com.puhuanyu.erp.myerp.util.RedisTemplateUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 *  @Description 自定义Realm（范围，权限），用于进行权限信息的验证
 *  @ClassName CustomRealm
 *  @Author 忠哥
 *  @data 2020-01-02 13:53
 */
public class CustomRealm extends AuthorizingRealm {
    @Autowired
    private RedisTemplateUtil redisTemplateUtil;
    @Autowired
    private RanksService ranksService;
    @Autowired
    private EmpService empService;

    /**
     * @Description 身份认证
     * @Param [authenticationToken]
     * @return org.apache.shiro.authc.AuthenticationInfo
     * @Author 忠哥
     * @Date 2020-1-3 10:11
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;//1.获取控制类传过来的主体
        String id = usernamePasswordToken.getUsername();//2.获取主体的用户名
        String reg = "[0-9]*";//3.正则表达式，判断账号是否为全数字
        if(id.matches(reg)){
            Emp emp = empService.findEmpById(Integer.parseInt(id));
            if(emp != null){//4.验证用户名和密码是否正确，空则返回控制类的UnknownAccountException等异常
                ByteSource salt = ByteSource.Util.bytes(emp.getName());
                SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(emp, emp.getPassword(), salt, getName());
                return simpleAuthenticationInfo;
            }else{
                return null;
            }
        }
        return null;
    }

    /**
     * @Description 权限认证，即当用户登录成功，将获取该用户的身份和权限
     * @Param [principalCollection] 用户的集合，包含了realm验证成功的用户信息
     * @return org.apache.shiro.authz.AuthorizationInfo 返回权限认证的信息
     * @Author 忠哥
     * @Date 2020-1-2 18:14
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权方法执行");
        Emp emp = (Emp) principalCollection.getPrimaryPrincipal();//获取员工对象
        List<Ranks> ranksList = null;
        if(redisTemplateUtil.findObject("Ranks", "Rank_id") == null){//数据库找ranks权限和角色的关系表
            ranksList = ranksService.findAllRanksByRank_id(emp.getRank().getId());
        }else {//从缓存中找权限和角色关系表
            ranksList = JSON.parseArray(String.valueOf(redisTemplateUtil.findObject("Ranks", "Rank_id")),Ranks.class);
        }
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();//权限认证的信息
        simpleAuthorizationInfo.addRole(emp.getRank().getName());//添加角色到权限认证
        for(Ranks ranks : ranksList){
            simpleAuthorizationInfo.addStringPermission(ranks.getRoot().getName());//添加权限到权限认证
        }
        return simpleAuthorizationInfo;
    }

    /**
     * * Authentication：身份认证/登录，验证用户是不是拥有相应的身份；
     *   Authorization：授权，即权限验证，验证某个已认证的用户是否拥有某个权限；即判断用户是否能做事情，
     *                  常见的如：验证某个用户是否拥有某个角色。或者细粒度的验证某个用户对某个资源是否具有某个权限；
     *   Session Manager：会话管理，即用户登录后就是一次会话，在没有退出之前，它的所有信息都在会话中；
     *                     会话可以是普通JavaSE环境的，也可以是如Web环境的；
     *   Cryptography：加密，保护数据的安全性，如密码加密存储到数据库，而不是明文存储；
     *   Web Support：Web支持，可以非常容易的集成到Web环境；
     *   Caching：缓存，比如用户登录后，其用户信息、拥有的角色/权限不必每次去查，这样可以提高效率；
     *   Concurrency：shiro支持多线程应用的并发验证，即如在一个线程中开启另一个线程，能把权限自动传播过去；
     *   Testing：提供测试支持；
     *   Run As：允许一个用户假装为另一个用户（如果他们允许）的身份进行访问；
     *   Remember Me：记住我，这个是非常常见的功能，即一次登录后，下次再来的话不用登录了。
     * */
}
