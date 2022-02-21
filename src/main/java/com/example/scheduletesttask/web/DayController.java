package com.example.scheduletesttask.web;


import com.example.scheduletesttask.domain.Day;
import com.example.scheduletesttask.repository.DayRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;


@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class DayController {


    private final DayRepository dayRepository;

    public DayController(DayRepository dayRepository) {
        this.dayRepository = dayRepository;
    }

    @PostMapping("/days")
    @ResponseStatus(HttpStatus.CREATED)
    public Day saveDay(@RequestBody Day day){
        return dayRepository.save(day);
    }

    @GetMapping("/days")
    @ResponseStatus(HttpStatus.OK)
    public List<Day> getAllDays(){
        return dayRepository.findAll();
    }

    @GetMapping("/days/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Day getDayById(@PathVariable Long id){
        return dayRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found day by id = " + id));
    }

    @PutMapping("/days/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Day updateDay(@PathVariable Long id, @RequestBody Day day){
        return dayRepository.findById(id)
                .map(entity -> {
                    entity.setName(day.getName());
                    return dayRepository.save(entity);
        })
                .orElseThrow(() -> new EntityNotFoundException("Not found day by id = " + id));
    }

    @DeleteMapping("/days/{id}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void removeStudentById(@PathVariable Long id){

        Day day = dayRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found day by id = " + id));
        dayRepository.delete(day);
    }

    @DeleteMapping("/days")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void removeAllStudents(){
        dayRepository.deleteAll();
    }
}
