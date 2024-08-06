package com.anucode.android.repository;

import com.anucode.android.entities.Subjects;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subjects, Integer> {
    Optional<Subjects> findByName(String subject);
}
