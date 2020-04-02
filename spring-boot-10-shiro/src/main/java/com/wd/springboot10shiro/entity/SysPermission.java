package com.wd.springboot10shiro.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author dwang
 * @create 2019-12-25 15:02
 */
@Entity
public class SysPermission implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;//主键.
    private String name;//名称.
    @Column(columnDefinition = "enum('menu','button')")
    private String resourceType;//资源类型，[menu|button]
    private String url;//资源路径.
    private String permission; //权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:view
    private Long parentId; //父编号
    private String parentIds; //父编号列表
    private Boolean available = Boolean.FALSE;
    @ManyToMany
    @JoinTable(name = "SysRolePermission", joinColumns = {@JoinColumn(name = "permissionId")}, inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private List<SysRole> roles;

    public Integer getId() {
        return id;
    }

    public SysPermission setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public SysPermission setName(String name) {
        this.name = name;
        return this;
    }

    public String getResourceType() {
        return resourceType;
    }

    public SysPermission setResourceType(String resourceType) {
        this.resourceType = resourceType;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public SysPermission setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getPermission() {
        return permission;
    }

    public SysPermission setPermission(String permission) {
        this.permission = permission;
        return this;
    }

    public Long getParentId() {
        return parentId;
    }

    public SysPermission setParentId(Long parentId) {
        this.parentId = parentId;
        return this;
    }

    public String getParentIds() {
        return parentIds;
    }

    public SysPermission setParentIds(String parentIds) {
        this.parentIds = parentIds;
        return this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public SysPermission setAvailable(Boolean available) {
        this.available = available;
        return this;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public SysPermission setRoles(List<SysRole> roles) {
        this.roles = roles;
        return this;
    }
}


