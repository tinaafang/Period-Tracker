package com.example.authenticationsystem.repository;

import com.example.authenticationsystem.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRole(String role);
}
