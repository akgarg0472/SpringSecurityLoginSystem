package com.akgarg.springsecurityloginsystem.repository;

import com.akgarg.springsecurityloginsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User as u WHERE u.email=:email")
    User getUserByEmail(@Param("email") String email);
}
