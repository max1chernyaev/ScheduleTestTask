package com.example.scheduletesttask.web;


import com.example.scheduletesttask.domain.Group;
import com.example.scheduletesttask.repository.GroupRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class GroupController {

    private final GroupRepository groupRepository;

    public GroupController(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @PostMapping("/groups")
    @ResponseStatus(HttpStatus.CREATED)
    public Group saveGroup(@RequestBody Group group){
        return groupRepository.save(group);
    }

    @GetMapping("/groups")
    @ResponseStatus(HttpStatus.OK)
    public List<Group> getAllGroups(){
        return groupRepository.findAll();
    }

    @GetMapping("/groups/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Group getGroupById(@PathVariable Long id){
        return groupRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found group by id = " + id));
    }

    @PutMapping ("/groups/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Group updateGroup(@PathVariable Long id, @RequestBody Group group){
        return groupRepository.findById(id)
                .map(entity -> {
                    entity.setName(group.getName());
                    return groupRepository.save(entity);
                })
                .orElseThrow(() -> new EntityNotFoundException("Not found group by id = " + id));
    }

    @DeleteMapping("/groups/{id}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void removeGroupById(@PathVariable Long id){

        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found group by id = " + id));
        groupRepository.delete(group);
    }

    @DeleteMapping("/groups")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void removeAllGroups(){
        groupRepository.deleteAll();
    }
}
