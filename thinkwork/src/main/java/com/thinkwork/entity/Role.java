package com.thinkwork.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "SYS_ROLE")
public class Role  extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(length = 64)
    private Integer sid;

    @Column(nullable = false, length = 128, unique = true)
    private String roleName;

    @Column(length = 512)
    private String description;

    @Column(nullable = false, length = 128)
    private String displayName;

    @ManyToMany
    @JoinTable(
            name = "sys_join_role_menu",
            joinColumns = {@JoinColumn(name = "role_sid")},
            inverseJoinColumns = {@JoinColumn(name = "menu_sid")})
    private List<Menu> menus;

    @ManyToMany(mappedBy = "roles", cascade = {CascadeType.ALL})
    private List<User> users;

    public Role(String roleName, String description, String displayName) {
        this.roleName = roleName;
        this.description = description;
        this.displayName = displayName;
    }

    public Role(Integer sid, String roleName, String description, String displayName) {
        this.sid = sid;
        this.roleName = roleName;
        this.description = description;
        this.displayName = displayName;
    }

    public Role(Integer sid, String roleName, String description, String displayName, List<Menu> menus, List<User> users) {
        this.sid = sid;
        this.roleName = roleName;
        this.description = description;
        this.displayName = displayName;
        this.menus = menus;
        this.users = users;
    }

    public Role() {
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Role{" +
                "sid=" + sid +
                ", roleName='" + roleName + '\'' +
                ", description='" + description + '\'' +
                ", displayName='" + displayName + '\'' +
                ", menus=" + menus +
                ", users=" + users +
                '}';
    }
}
