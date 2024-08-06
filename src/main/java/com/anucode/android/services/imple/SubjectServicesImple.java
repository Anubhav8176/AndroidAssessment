package com.anucode.android.services.imple;

import com.anucode.android.entities.Subjects;
import com.anucode.android.repository.SubjectRepository;
import com.anucode.android.services.SubjectServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServicesImple implements SubjectServices {

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public List<Subjects> getAllSubjects() {
        return subjectRepository.findAll();
    }
}
