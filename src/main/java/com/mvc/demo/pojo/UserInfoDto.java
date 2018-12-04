package com.mvc.demo.pojo;

import java.io.Serializable;

/**
 * @author Dawei 2018/12/1
 */
public class UserInfoDto implements Serializable {
    private Long id;
    /**
     * 用户的登陆名
     */
    private String loginName;

    private String userName;
    /* 密码 */
    private String passWord;

    /* 加权盐值 */
    private String makeSalt;

    /* 是否记住登陆信息 */
    private Integer rememberType;

    /* 登陆的主机 信息 例如：IP */
    private String loginHost;

    public UserInfoDto() {
    }

    public UserInfoDto(Long id, String loginName, String userName, String passWord, String makeSalt, Integer rememberType, String loginHost) {
        this.id = id;
        this.loginName = loginName;
        this.userName = userName;
        this.passWord = passWord;
        this.makeSalt = makeSalt;
        this.rememberType = rememberType;
        this.loginHost = loginHost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public Integer getRememberType() {
        return rememberType;
    }

    public void setRememberType(Integer rememberType) {
        this.rememberType = rememberType;
    }

    public String getLoginHost() {
        return loginHost;
    }

    public void setLoginHost(String loginHost) {
        this.loginHost = loginHost;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getMakeSalt() {
        return makeSalt;
    }

    public void setMakeSalt(String makeSalt) {
        this.makeSalt = makeSalt;
    }
}

