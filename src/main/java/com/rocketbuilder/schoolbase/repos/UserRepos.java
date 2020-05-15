package com.rocketbuilder.schoolbase.repos;

import com.rocketbuilder.schoolbase.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepos extends CrudRepository<User, Long> {

    boolean existsByLogin(String login);
    User findByLogin(String login);
}
