package com.company.fantasyturnedreal.repository.user;

import com.company.fantasyturnedreal.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
