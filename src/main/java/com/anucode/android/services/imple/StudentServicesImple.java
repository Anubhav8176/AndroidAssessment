package com.anucode.android.services.imple;

import com.anucode.android.entities.Student;
import com.anucode.android.entities.Subjects;
import com.anucode.android.model.StudentDTO;
import com.anucode.android.repository.StudentRepository;
import com.anucode.android.repository.SubjectRepository;
import com.anucode.android.services.StudentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class StudentServicesImple implements StudentServices {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }


    @Override
    public Student addStudent(StudentDTO studentDTO) {
        Student student = new Student();

        //Checking for the subjects are present in the database or not.
        Set<Subjects> studentDTOsub = studentDTO.getSubjects();
        Set<Subjects> subjects = new HashSet<>();

        for(Subjects sub: studentDTOsub){
            if(sub.getId()!=null){
                Subjects subjects1 = subjectRepository.findById(sub.getId())
                        .orElseThrow(()-> new RuntimeException("Subject not found."));

                subjects.add(subjects1);
            }else {
                Optional<Subjects> existingSubjectOpt = subjectRepository.findByName(sub.getName());
                if (existingSubjectOpt.isPresent()) {
                    subjects.add(existingSubjectOpt.get());
                } else {
                    Subjects newSubject = subjectRepository.save(sub);
                    subjects.add(newSubject);
                }
            }
        }


        student.setName(studentDTO.getName());
        student.setAddress(studentDTO.getAddress());
        student.setSubjects(subjects);
        studentRepository.save(student);
        return student;
    }



    @Override
    public Set<Subjects> getAllSubjectsByName(String studentName) {
        Student student = studentRepository.findByName(studentName)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return student.getSubjects();
    }
}
