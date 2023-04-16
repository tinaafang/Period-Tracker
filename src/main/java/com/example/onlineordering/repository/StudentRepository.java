package com.example.onlineordering.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.onlineordering.Student;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends ListCrudRepository<Student, Integer> {

}
