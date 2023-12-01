package com.example.jee_laboratorium_8.student;

import java.util.List;

public interface StudentService {
    List<StudentDto> getAllStudents();
    public List<StudentDto> getAllStudentsNoAttachments();
}
