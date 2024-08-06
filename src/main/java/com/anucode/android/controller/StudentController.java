package com.anucode.android.controller;


import com.anucode.android.entities.Subjects;
import com.anucode.android.model.StudentDTO;
import com.anucode.android.services.StudentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentServices stuServices;

    @GetMapping("/getAllStudents")
    public ResponseEntity getAllStudents(){
        return new ResponseEntity<>(stuServices.getAllStudents(), HttpStatus.OK);
    }

    @GetMapping("/getAllSub")
    public ResponseEntity<Set<Subjects>> getAllSubjects(@RequestParam String studentName) {
        Set<Subjects> subjects = stuServices.getAllSubjectsByName(studentName);
        return new ResponseEntity<>(subjects, HttpStatus.OK);
    }

    @PostMapping("/addStudent")
    public ResponseEntity addStudent(@RequestBody StudentDTO studentDTO){
        return new ResponseEntity<>(stuServices.addStudent(studentDTO), HttpStatus.OK);
    }


}
