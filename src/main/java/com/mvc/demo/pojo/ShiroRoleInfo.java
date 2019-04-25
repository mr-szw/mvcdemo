package com.mvc.demo.pojo;

public class ShiroRoleInfo {
    private Long id;

    private String roleName;

    public ShiroRoleInfo(Long id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public ShiroRoleInfo() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }
}