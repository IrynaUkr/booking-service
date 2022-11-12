package com.epam.cinema.repository;

import com.epam.cinema.model.impl.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

     @Override
     Optional<UserAccount> findById(Long id);
}
