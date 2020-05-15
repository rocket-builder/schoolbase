package com.rocketbuilder.schoolbase.repos;

import com.rocketbuilder.schoolbase.models.Teacher;
import org.springframework.data.repository.CrudRepository;

public interface TeacherRepos extends CrudRepository <Teacher, Long> {

    Teacher findDistinctBySurname(String surname);
    Teacher findById(long id);
}
