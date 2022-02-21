package com.example.scheduletesttask.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id", nullable = false)
    private Long groupId;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    private Day day;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Student> students = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Subject> subjects = new HashSet<>();


}
