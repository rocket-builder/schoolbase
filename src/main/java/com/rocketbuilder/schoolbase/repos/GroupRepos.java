package com.rocketbuilder.schoolbase.repos;

import com.rocketbuilder.schoolbase.models.Groups;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GroupRepos extends CrudRepository<Groups, Long> {

    Groups findDistinctByTitle(String title);

    List<Groups> findAll();

}
