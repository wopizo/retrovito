package project.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "user")
public class User extends TableModel implements UserDetails {
    @NotNull
    @Column(name = "username")
    private String username;

    @NotNull
    @Column(name = "password")
    private String password;

    @Column(name = "active")
    private boolean active;

    @NotNull
    @Column(name = "name")
    private String name;

    @Column(name = "sname")
    private String sname;

    @Column(name = "fname")
    private String fname;

    @NotNull
    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name = "phone")
    private String phone;

    @Column(name = "picture")
    private String picture;

    @Column(name = "activationmess")
    private String activationMessage;

    @Column(name = "date")
//    @CreationTimestamp
    private Date date;

    @NotNull
    @Column(name = "city")
    private String city;



    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @PrePersist
    private void onCreate(){
        if(date == null){
            date = new Date();
        }
    }

    public boolean isAdmin(){
        return roles.contains(Role.ADMIN);
    }
    public boolean isBlocked(){
        return roles.contains(Role.BLOCKED);
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getActivationMessage() {
        return activationMessage;
    }

    public void setActivationMessage(String activationMessage) {
        this.activationMessage = activationMessage;
    }
}

