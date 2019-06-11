package com.thinkwork.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "SYS_MENU_GROUP")
public class MenuGroup  extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(length = 64)
    private Integer sid;

    @Column(length = 256)
    private String name;

    @Column(length = 256)
    private String description;

    @Column(length = 64)
    private Integer parentId;

    @Column(length = 2)
    private Boolean isRoot;

    @OneToMany(mappedBy = "menuGroup", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name="menu_group_id",referencedColumnName = "sid")
    private List<Menu> menuList;

    public MenuGroup(Integer sid, String name, String description, Integer parentId, Boolean isRoot) {
        this.sid = sid;
        this.name = name;
        this.description = description;
        this.parentId = parentId;
        this.isRoot = isRoot;
    }

    public MenuGroup(String name, String description, Integer parentId, Boolean isRoot) {
        this.name = name;
        this.description = description;
        this.parentId = parentId;
        this.isRoot = isRoot;
    }


    public MenuGroup(Integer sid, String name, String description, Integer parentId, Boolean isRoot, List<Menu> menuList) {
        this.sid = sid;
        this.name = name;
        this.description = description;
        this.parentId = parentId;
        this.isRoot = isRoot;
        this.menuList = menuList;
    }

    public MenuGroup() {
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    public Boolean getRoot() {
        return isRoot;
    }

    public void setRoot(Boolean root) {
        isRoot = root;
    }

    @Override
    public String toString() {
        return "MenuGroup{" +
                "sid=" + sid +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", parentId=" + parentId +
                ", isRoot=" + isRoot +
                ", menuList=" + menuList +
                '}';
    }
}
