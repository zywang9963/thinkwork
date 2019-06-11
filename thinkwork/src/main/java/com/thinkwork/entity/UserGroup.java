package com.thinkwork.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "SYS_USER_GROUP")
public class UserGroup  extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(length = 64)
    private Integer sid;

    @Column(nullable = false, length = 128, unique = true)
    private String groupName;

    @Column(length = 512)
    private String description;

    @Column(nullable = false, length = 128)
    private String displayName;

    @Column(nullable = false, length = 128)
    private Integer parentId;

    @ManyToMany(mappedBy = "userGroups", cascade = {CascadeType.ALL})
    private List<User> users;

    public UserGroup(String groupName, String description, String displayName, Integer parentId) {
        this.groupName = groupName;
        this.description = description;
        this.displayName = displayName;
        this.parentId = parentId;
    }

    public UserGroup(Integer sid, String groupName, String description, String displayName, Integer parentId) {
        this.sid = sid;
        this.groupName = groupName;
        this.description = description;
        this.displayName = displayName;
        this.parentId = parentId;
    }

    public UserGroup(Integer sid, String groupName, String description, String displayName, Integer parentId, List<User> users) {
        this.sid = sid;
        this.groupName = groupName;
        this.description = description;
        this.displayName = displayName;
        this.parentId = parentId;
        this.users = users;
    }

    public UserGroup() {
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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

    @Override
    public String toString() {
        return "UserGroup{" +
                "sid=" + sid +
                ", groupName='" + groupName + '\'' +
                ", description='" + description + '\'' +
                ", displayName='" + displayName + '\'' +
                ", parentId=" + parentId +
                ", users=" + users +
                '}';
    }
}
