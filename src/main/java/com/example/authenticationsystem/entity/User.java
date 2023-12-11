package com.example.authenticationsystem.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Integer id;


    @Column(name = "USERNAME")
    private String userName;


    @Column(name = "EMAIL",unique = true)
    private String email;


    @Column(name = "PASSWORD")
    private String password;


    @Column(name = "ENABLED")
    private Boolean enabled;

    @OneToMany(mappedBy="user")
//    @JsonSerialize(using = PeriodSerializer.class)
    private Set<Period> periods;





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

    @JsonIgnore
    public String getPassword() {
        return password;
    }
    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }


    public String getUsername() {
        return null;
    }
    @JsonIgnore
    public boolean isEnabled() {
        return enabled;
    }
    @JsonProperty
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    @JsonIgnore
    public Set<Period> getPeriods() {
        return periods;
    }

    @JsonProperty
    public void setPeriods(Set<Period> periods) {
        this.periods = periods;
    }
}
