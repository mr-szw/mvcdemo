package com.mvc.demo.entity;

/**
 * @author Dawei 2018/12/1
 * 数据库实体
 */
public class UserInfo {

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

    /* 记住密码 类型*/
    private Integer rememberType;

    /* ip 地址 */
    private String ipAddress;

    public UserInfo() {
    }

    public UserInfo(Long id, String loginName, String userName, String passWord, String makeSalt, Integer rememberType, String ipAddress) {
        this.id = id;
        this.loginName = loginName;
        this.userName = userName;
        this.passWord = passWord;
        this.makeSalt = makeSalt;
        this.rememberType = rememberType;
        this.ipAddress = ipAddress;
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

    public Integer getRememberType() {
        return rememberType;
    }

    public void setRememberType(Integer rememberType) {
        this.rememberType = rememberType;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
