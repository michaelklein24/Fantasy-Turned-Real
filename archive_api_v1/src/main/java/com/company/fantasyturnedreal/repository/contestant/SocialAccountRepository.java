package com.company.fantasyturnedreal.repository.contestant;

import com.company.fantasyturnedreal.model.contestant.SocialAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialAccountRepository extends JpaRepository<SocialAccount, Long> {

}
