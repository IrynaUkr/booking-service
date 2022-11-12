package com.epam.cinema.repository;

import com.epam.cinema.model.impl.UserModel;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserModel, Long> {

    Optional<UserModel> getUserByEmail(String email);

    List<UserModel> getUsersByName(String name);
}
