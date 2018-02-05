package com.example.demo.repository;

import com.example.demo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by msav on 12/29/2017.
 */
@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Integer>{

    Role findByRoleName(String role);

}
