package com.wd.springboot10shiro.entity;

import javax.persistence.*;
import java.util.List;

/**
 * @author dwang
 * @create 2019-12-25 15:02
 */
@Entity
public class SysRole {
    @Id
    @GeneratedValue
    private Integer id; // 编号
    private String role; // 角色标识程序中判断使用,如"admin",这个是唯一的:
    private String description; // 角色描述,UI界面显示使用
    private Boolean available = Boolean.FALSE; // 是否可用,如果不可用将不会添加给用户

    //角色 -- 权限关系：多对多关系;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "SysRolePermission", joinColumns = {@JoinColumn(name = "roleId")}, inverseJoinColumns = {@JoinColumn(name = "permissionId")})
    private List<SysPermission> permissions;

    // 用户 - 角色关系定义;
    @ManyToMany
    @JoinTable(name = "SysUserRole", joinColumns = {@JoinColumn(name = "roleId")}, inverseJoinColumns = {@JoinColumn(name = "uid")})
    private List<UserInfo> userInfos;// 一个角色对应多个用户


    public Integer getId() {
        return id;
    }

    public SysRole setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getRole() {
        return role;
    }

    public SysRole setRole(String role) {
        this.role = role;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SysRole setDescription(String description) {
        this.description = description;
        return this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public SysRole setAvailable(Boolean available) {
        this.available = available;
        return this;
    }

    public List<SysPermission> getPermissions() {
        return permissions;
    }

    public SysRole setPermissions(List<SysPermission> permissions) {
        this.permissions = permissions;
        return this;
    }

    public List<UserInfo> getUserInfos() {
        return userInfos;
    }

    public SysRole setUserInfos(List<UserInfo> userInfos) {
        this.userInfos = userInfos;
        return this;
    }
}
