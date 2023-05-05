package com.example.recipewebsite.entity;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name="ROLE")
public class Role
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ROL_NUM")
    private Integer id;

    @Column(name="ROLE",nullable=false, unique=true)
    private String role;

    @ManyToMany(mappedBy="roles")
    private List<User> users;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
