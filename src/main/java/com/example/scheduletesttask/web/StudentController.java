package com.example.scheduletesttask.web;

import com.example.scheduletesttask.domain.Student;
import com.example.scheduletesttask.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;


@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @PostMapping("/students")
    @ResponseStatus(HttpStatus.CREATED)
    public Student saveStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    @GetMapping("/students")
    @ResponseStatus(HttpStatus.OK)
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/students/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Student getStudentById(@PathVariable Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found student with id = " + id));
        return student;
    }

    @PutMapping("/students/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Student updateStudents(@PathVariable Long id, @RequestBody Student student) {

        return studentRepository.findById(id)
                .map(entity -> {
                    entity.setFirstName(student.getFirstName());
                    entity.setLastName(student.getLastName());
                    return studentRepository.save(entity);
                })
                .orElseThrow(() -> new EntityNotFoundException("Not found student by id = " + id));
    }

    @DeleteMapping("/students/{id}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void removeStudentById(@PathVariable Long id){

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found student by id = " + id));
        studentRepository.delete(student);
    }

    @DeleteMapping("/students")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void removeAllStudents(){
        studentRepository.deleteAll();
    }


}
