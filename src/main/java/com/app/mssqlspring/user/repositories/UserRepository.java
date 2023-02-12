package com.app.mssqlspring.user.repositories;

import com.app.mssqlspring.user.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
    User findByName(String name);
}
