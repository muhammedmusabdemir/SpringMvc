package com.tpe.service;

import com.tpe.domain.Student;
import com.tpe.repository.StudentRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service //daha ozel ve galismis
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository repository;

    @Override
    public void saveStudent(Student student) {

        repository.save(student);

    }

    @Override
    public List<Student> getAllStudent() {
        return null;
    }

    @Override
    public Student getStudentById(Long id) {
        return null;
    }

    @Override
    public void deleteStudent(Long id) {

    }
}
