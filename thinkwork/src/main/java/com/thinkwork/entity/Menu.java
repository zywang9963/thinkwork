package com.thinkwork.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "SYS_MENU")
public class Menu extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(length = 64)
    private Integer sid;

    @Column(nullable = false, length = 256, unique = true)
    private String menuName;

    @Column(nullable = false, length = 128)
    private String displayName;

    @Column(length = 512)
    private String description;

    @Column(nullable = false, length = 512)
    private String url;

    @Column(length = 512)
    private Date dateCreated;

    @Column(length = 64)
    private Date dateModified;

    @Column(nullable = false, length = 3)
    private boolean isActive;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    @JoinColumn(name = "menu_group_id")
    private MenuGroup menuGroup;

    @ManyToMany(mappedBy = "menus")
    private List<Role> roles;

    public Menu(String menuName, String displayName, String description, String url, Date dateCreated, Date dateModified, boolean isActive) {
        this.menuName = menuName;
        this.displayName = displayName;
        this.description = description;
        this.url = url;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.isActive = isActive;
    }

    public Menu(Integer sid, String menuName, String displayName, String description, String url, Date dateCreated, Date dateModified, boolean isActive) {
        this.sid = sid;
        this.menuName = menuName;
        this.displayName = displayName;
        this.description = description;
        this.url = url;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.isActive = isActive;
    }

    public Menu(Integer sid, String menuName, String displayName, String description, String url, Date dateCreated, Date dateModified, boolean isActive, MenuGroup menuGroup, List<Role> roles) {
        this.sid = sid;
        this.menuName = menuName;
        this.displayName = displayName;
        this.description = description;
        this.url = url;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.isActive = isActive;
        this.menuGroup = menuGroup;
        this.roles = roles;
    }

    public Menu() {
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public MenuGroup getMenuGroup() {
        return menuGroup;
    }

    public void setMenuGroup(MenuGroup menuGroup) {
        this.menuGroup = menuGroup;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "sid=" + sid +
                ", menuName='" + menuName + '\'' +
                ", displayName='" + displayName + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", dateCreated=" + dateCreated +
                ", dateModified=" + dateModified +
                ", isActive=" + isActive +
                ", menuGroup=" + menuGroup +
                ", roles=" + roles +
                '}';
    }
}
