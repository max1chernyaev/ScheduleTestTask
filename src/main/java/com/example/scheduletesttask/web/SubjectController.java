package com.example.scheduletesttask.web;

import com.example.scheduletesttask.domain.Subject;
import com.example.scheduletesttask.repository.SubjectRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class SubjectController {

    private final SubjectRepository subjectRepository;

    public SubjectController(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @PostMapping("/subjects")
    @ResponseStatus(HttpStatus.CREATED)
    public Subject saveSubject(@RequestBody Subject sub){
        return subjectRepository.save(sub);
    }

    @GetMapping("/subjects")
    @ResponseStatus(HttpStatus.OK)
    public List<Subject> getAllSubjects(){
        return subjectRepository.findAll();
    }

    @GetMapping("/subjects/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Subject getSubById(@PathVariable Long id){
        return subjectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found sub by id = " + id));
    }

    @PostMapping("/subjects/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Subject updateSub(@PathVariable Long id, @RequestBody Subject sub){
        return subjectRepository.findById(id)
                .map(entity -> {
                    entity.setName(sub.getName());
                    return subjectRepository.save(entity);
                })
                .orElseThrow(() -> new EntityNotFoundException("Not found sub by id = " + id));
    }

    @DeleteMapping("/subjects/{id}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void removeById(@PathVariable Long id){

        Subject sub = subjectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found sub by id = " + id));
        subjectRepository.delete(sub);
    }

    @DeleteMapping("/subjects")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void removeAllSubs(){
        subjectRepository.deleteAll();
    }
}
