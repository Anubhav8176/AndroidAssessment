package com.anucode.android.repository;

import com.anucode.android.entities.StudentAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentAuthRepository extends JpaRepository<StudentAuth, Integer> {
    StudentAuth findByName(String name);
}
