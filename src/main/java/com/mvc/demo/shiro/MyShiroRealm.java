package com.mvc.demo.shiro;

import com.mvc.demo.mapper.ShiroPermissionInfoMapper;
import com.mvc.demo.mapper.ShiroRoleInfoMapper;
import com.mvc.demo.mapper.ShiroUserInfoMapper;
import com.mvc.demo.pojo.ShiroPermissionInfo;
import com.mvc.demo.pojo.ShiroRoleInfo;
import com.mvc.demo.pojo.ShiroUserInfo;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * @author Dawei 2018/12/1
 */
@Component
public class MyShiroRealm extends AuthorizingRealm {


    @Resource
    private ShiroUserInfoMapper userInfoMapper;
    @Resource
    private ShiroRoleInfoMapper shiroRoleInfoMapper;
    @Resource
    private ShiroPermissionInfoMapper shiroPermissionInfoMapper;

    /**
     * 为当前登陆成功用户获取信息授权
     *
     * @param principals 主要信息集合
     * @return 权限和角色信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //1、通过主体获取用户名
        String loginName = (String) principals.getPrimaryPrincipal();
        ShiroUserInfo userInfo = getUserInfoByLoginName(loginName);
        if (userInfo != null) {
            SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
            Long userInfoId = userInfo.getId();
            if(userInfoId != null) {
                //2、通过用户名获取 角色信息
                Set<String> userRolesSet = getUserRolesByUserId(userInfoId);
                if(!CollectionUtils.isEmpty(userRolesSet)) {
                    //3、通过用户名获取 权限数据
                    Set<String> userPermissionSet = getUserPermissionByUserId(userInfoId);
                    authorizationInfo.setRoles(userRolesSet);
                    authorizationInfo.setStringPermissions(userPermissionSet);
                    return authorizationInfo;
                }
            }
        }
        return null;
    }

    /**
     * 获取认证信息认证
     * 创建SecuityManager --> 主体提交认证 --> SecurityManager进行认证 -->Authentictor进行认证判断 --> 认证数据依赖于Realm
     *
     * @param authenticationToken 主体获取的认证信息
     * @return 认证成功返回认证信息 认证失败 返回空
     * @throws AuthenticationException 认证失败的异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //1、从主题传过来的认证信息中 获取用户名
        String loginName = (String) authenticationToken.getPrincipal();
        //2、通过用户名在数据库中获取凭证
        ShiroUserInfo userInfo = getUserInfoByLoginName(loginName);
        if (userInfo != null && !StringUtils.isEmpty(userInfo.getPassWord())) {
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(loginName, userInfo.getPassWord(), "myShiroRealm");
            //加盐进行加密
            authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(userInfo.getMakeSalt()));
            return authenticationInfo;
        }
        return null;
    }


    private ShiroUserInfo getUserInfoByLoginName(String loginName) {
        if(!StringUtils.isEmpty(loginName)) {
            return userInfoMapper.getUserInfoByLoginName(loginName);
        }
        return null;
    }

    /**
     * 获取角色信息
     * @param userId userID
     * @return 返角色内容
     */
    private Set<String> getUserRolesByUserId(Long userId) {
        Set<String> roles = new HashSet<>();
        if(userId != null) {
            List<ShiroRoleInfo> shiroRoleInfoList = shiroRoleInfoMapper.getRolesByUserId(userId);
            if (!CollectionUtils.isEmpty(shiroRoleInfoList)) {
                shiroRoleInfoList.forEach(roleInfo -> roles.add(roleInfo.getRoleName()));
            }
            return roles;
        }
        return null;
    }

    /**
     * 获取权限信息
     * @param userId 通过用户ID获权限信息
     * @return 返回权限信息
     */
    private Set<String> getUserPermissionByUserId(Long userId) {
        Set<String> permissionSet = new HashSet<>();
        if(userId != null) {
            List<ShiroPermissionInfo> shiroPermissionInfoList = shiroPermissionInfoMapper.getPermissonListByUserId(userId);
            if (!CollectionUtils.isEmpty(shiroPermissionInfoList)) {
                shiroPermissionInfoList.forEach(permissionInfo -> permissionSet.add(permissionInfo.getPermissionName()));
                return permissionSet;
            }
        }
        return null;
    }
}
