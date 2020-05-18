package com.rocketbuilder.schoolbase.repos;

import com.rocketbuilder.schoolbase.models.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepos extends CrudRepository<Student, Long> {

    Student findAllBySurname(String surname);
    Student findById(long id);

    List<Student> findBySurnameContainsIgnoreCase(String match);
}
