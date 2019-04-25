package com.mvc.demo.pojo;

public class ShiroPermissionInfo {
    private Long id;

    private String permissionName;

    private Long roleId;

    public ShiroPermissionInfo(Long id, String permissionName, Long roleId) {
        this.id = id;
        this.permissionName = permissionName;
        this.roleId = roleId;
    }

    public ShiroPermissionInfo() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName == null ? null : permissionName.trim();
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}