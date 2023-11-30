package com.example.jee_laboratorium_8.student;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;
    private final StudentConverter studentConverter;
    private StudentMapper studentMapper;


    @Override
    public List<StudentDto> getAllStudents() {
        return studentRepository.findAll().stream().map(studentMapper.INSTANCE::mapStudentToStudentDto)
                .collect(Collectors.toList());
    }
}
