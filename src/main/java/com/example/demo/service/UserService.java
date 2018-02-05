package com.example.demo.service;

import com.example.demo.model.User;

/**
 * Created by msav on 12/29/2017.
 */
public interface UserService {

    User findUserByEmail(String email);
    void saveUser(User user);

}
