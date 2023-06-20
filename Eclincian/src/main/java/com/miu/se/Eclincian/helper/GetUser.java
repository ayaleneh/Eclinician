package com.miu.se.Eclincian.helper;


import com.miu.se.Eclincian.entity.User;
import com.miu.se.Eclincian.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class GetUser {// a utility function that retrieves the currently authenticated User object from our application's security context.

    @Autowired
    UserRepository userRepository;
    public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            System.out.println("username wil be: "+authentication.getDetails());
            return userRepository.findUserByEmailAddress(username);
        }

        return null;
    }

}

