package com.caopan.TrainSys.model;



public class Role {

    private Integer roleId;
    private String role;
    private Integer authority;

    public Role() {
        this.roleId = 0;
        this.role = "";
        this.authority = 0;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getAuthority() {
        return authority;
    }

    public void setAuthority(int authority) {
        this.authority = authority;
    }


}
