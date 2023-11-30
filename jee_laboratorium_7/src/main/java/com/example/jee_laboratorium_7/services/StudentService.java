package com.example.jee_laboratorium_7.services;

import com.example.jee_laboratorium_7.entities.Student;
import com.example.jee_laboratorium_7.entities.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getStudentList() {
        return (List<Student>) studentRepository.findAll();
    }

    public void addStudent(Student newStudent) {
        studentRepository.save(newStudent);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public void updateStudent(Long id, Student updatedStudent) {
        Student existingStudent = studentRepository.findById(id).orElse(null);

        if (existingStudent != null) {
            existingStudent.setName(updatedStudent.getName());
            existingStudent.setSurname(updatedStudent.getSurname());
            existingStudent.setAverage(updatedStudent.getAverage());

            studentRepository.save(existingStudent);
        }
    }
}
