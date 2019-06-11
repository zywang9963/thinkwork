package com.thinkwork.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "SYS_USER")
public class User extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(length = 64)
    private Integer sid;

    @Column(nullable = false, length = 128, unique = true)
    private String userName;

    @Column(length = 20)
    private Date birthday;

    @Column(nullable = false, length = 128, unique = true)
    private String email;

    @Column(nullable = false, length = 512)
    private String password;


    @Column(length = 128)
    private String firstName;

    @Column(length = 128)
    private String lastName;

    @Column(nullable = false, length = 2)
    private boolean isActive;

    @Column(nullable = false, length = 2)
    private boolean isExpired;

    @Column(length = 64)
    private Date dateCreated;

    @Column(length = 64)
    private Date dateModified;

    @Column(length = 512)
    private String img;

    @Column(length = 2)//values - 1 - male, 2 - female, 3 - others
    private Integer gender;

    @Column(length = 128)
    private String telephone;

    @Column(length = 128)
    private String mobile;

    @ManyToMany
    @JoinTable(
            name = "sys_join_user_role",
            joinColumns = {@JoinColumn(name = "user_sid")},
            inverseJoinColumns = {@JoinColumn(name = "role_sid")})
    private List<Role> roles;

    @ManyToMany
    @JoinTable(
            name = "sys_join_user_group",
            joinColumns = {@JoinColumn(name = "user_sid")},
            inverseJoinColumns = {@JoinColumn(name = "group_sid")})
    private List<UserGroup> userGroups;

    public User(String userName, Date birthday, String email, String password, String firstName, String lastName, boolean isActive, boolean isExpired, Date dateCreated, Date dateModified, String img, Integer gender, String telephone, String mobile) {
        this.userName = userName;
        this.birthday = birthday;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isActive = isActive;
        this.isExpired = isExpired;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.img = img;
        this.gender = gender;
        this.telephone = telephone;
        this.mobile = mobile;
    }

    public User(Integer sid, String userName, Date birthday, String email, String password, String firstName, String lastName, boolean isActive, boolean isExpired, Date dateCreated, Date dateModified, String img, Integer gender, String telephone, String mobile) {
        this.sid = sid;
        this.userName = userName;
        this.birthday = birthday;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isActive = isActive;
        this.isExpired = isExpired;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.img = img;
        this.gender = gender;
        this.telephone = telephone;
        this.mobile = mobile;
    }

    public User(Integer sid, String userName, Date birthday, String email, String password, String firstName, String lastName, boolean isActive, boolean isExpired, Date dateCreated, Date dateModified, String img, Integer gender, String telephone, String mobile, List<Role> roles, List<UserGroup> userGroups) {
        this.sid = sid;
        this.userName = userName;
        this.birthday = birthday;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isActive = isActive;
        this.isExpired = isExpired;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.img = img;
        this.gender = gender;
        this.telephone = telephone;
        this.mobile = mobile;
        this.roles = roles;
        this.userGroups = userGroups;
    }

    public User() {
        this.dateCreated = new java.util.Date();
        this.dateModified = new java.util.Date();
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<UserGroup> getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(List<UserGroup> userGroups) {
        this.userGroups = userGroups;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }


    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isExpired() {
        return isExpired;
    }

    public void setExpired(boolean expired) {
        isExpired = expired;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "sid=" + sid +
                ", userName='" + userName + '\'' +
                ", birthday=" + birthday +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", isActive=" + isActive +
                ", isExpired=" + isExpired +
                ", dateCreated=" + dateCreated +
                ", dateModified=" + dateModified +
                ", img='" + img + '\'' +
                ", gender=" + gender +
                ", telephone='" + telephone + '\'' +
                ", mobile='" + mobile + '\'' +
                ", roles=" + roles +
                ", userGroups=" + userGroups +
                '}';
    }
}
