package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by msav on 12/29/2017.
 */
@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer>{

    User findByEmail(String email);

}
