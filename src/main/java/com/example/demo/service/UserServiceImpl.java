package com.example.demo.service;

import com.example.demo.model.Role;
import com.example.demo.model.Roles;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;

/**
 * Created by msav on 12/29/2017.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        createRole(Roles.ADMIN);
        createRole(Roles.USER);

        Role adminRole = roleRepository.findByRoleName(Roles.USER.getValue());
        user.setRoles(new HashSet<>(Collections.singletonList(adminRole)));

        userRepository.save(user);
    }

    private void createRole(Roles roleName) {
        Role role = new Role();
        role.setRoleName(roleName.getValue());
        roleRepository.save(role);
    }
}
