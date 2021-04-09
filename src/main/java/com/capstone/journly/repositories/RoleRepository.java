package com.capstone.journly.repositories;

import com.capstone.journly.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findRoleById(long id);

}
