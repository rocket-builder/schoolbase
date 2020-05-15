package com.rocketbuilder.schoolbase.repos;

import com.rocketbuilder.schoolbase.models.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepos extends CrudRepository<Student, Long> {

    Student findAllBySurname(String surname);
    Student findById(long id);
}
