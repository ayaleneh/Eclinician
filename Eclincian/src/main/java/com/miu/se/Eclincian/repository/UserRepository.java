package com.miu.se.Eclincian.repository;

import com.miu.se.Eclincian.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Long> {


    User findByEmail(String email);
    @Query("select u from User u where u.email= :email")
    User findUserByEmailAddress(String email);

}
