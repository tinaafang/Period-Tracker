package com.example.authentication.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USR_NUM")
    private Integer id;

    @NotNull
    @NotEmpty
    @Column(name = "USERNAME")
    private String userName;

    @NotNull
    @NotEmpty
    @Column(name = "EMAIL",unique = true)
    private String email;

    @NotNull
    @NotEmpty
    @Column(name = "HASHED_PSWD")
    private String password;


    @Column(name = "ENABLED")
    private Boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(
            name="ASSOC_USR_ROL",
            joinColumns={@JoinColumn(name="USR_NUM", referencedColumnName="USR_NUM")},
            inverseJoinColumns={@JoinColumn(name="ROL_NUM", referencedColumnName="ROL_NUM")})
    private List<Role> roles = new ArrayList<>();





    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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


    public String getUsername() {
        return null;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
