package com.example.jee_laboratorium_8.student;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping(value = "/students")
@RestController
public class StudentController {
    private final StudentServiceImpl studentService;

    @GetMapping("")
    public List<StudentDto> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/noattachments")
    public List<StudentDto> getAllStudentsNoAttachments(){
        return studentService.getAllStudentsNoAttachments();
    }
}
