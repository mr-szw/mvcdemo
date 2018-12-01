package com.mvc.demo.entity;

/**
 * @author Dawei 2018/12/1
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
}
