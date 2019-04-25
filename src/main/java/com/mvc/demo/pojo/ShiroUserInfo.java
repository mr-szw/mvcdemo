package com.mvc.demo.pojo;

public class ShiroUserInfo {
    private Long id;

    private String loginName;

    private String userName;

    private String passWord;

    private String makeSalt;

    private Integer rememberType;

    private String ipAddress;

    private String phoneNum;

    private String emailAddress;

    public ShiroUserInfo(Long id, String loginName, String userName, String passWord, String makeSalt, Integer rememberType, String ipAddress, String phoneNum, String emailAddress) {
        this.id = id;
        this.loginName = loginName;
        this.userName = userName;
        this.passWord = passWord;
        this.makeSalt = makeSalt;
        this.rememberType = rememberType;
        this.ipAddress = ipAddress;
        this.phoneNum = phoneNum;
        this.emailAddress = emailAddress;
    }

    public ShiroUserInfo() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord == null ? null : passWord.trim();
    }

    public String getMakeSalt() {
        return makeSalt;
    }

    public void setMakeSalt(String makeSalt) {
        this.makeSalt = makeSalt == null ? null : makeSalt.trim();
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
        this.ipAddress = ipAddress == null ? null : ipAddress.trim();
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum == null ? null : phoneNum.trim();
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress == null ? null : emailAddress.trim();
    }
}