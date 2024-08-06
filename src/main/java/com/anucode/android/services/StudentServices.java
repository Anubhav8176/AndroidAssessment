package com.anucode.android.services;

import com.anucode.android.entities.Student;
import com.anucode.android.entities.Subjects;
import com.anucode.android.model.StudentDTO;

import java.util.List;
import java.util.Set;

public interface StudentServices {
    List<Student> getAllStudents();
    Student addStudent(StudentDTO studentDTO);
    Set<Subjects> getAllSubjectsByName(String studentName);
}
