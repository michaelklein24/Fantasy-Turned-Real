package com.company.fantasyturnedreal.repository.user;

import com.company.fantasyturnedreal.model.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
