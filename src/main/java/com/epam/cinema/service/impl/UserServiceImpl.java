package com.epam.cinema.service.impl;

import com.epam.cinema.exception.UserAlreadyExistException;
import com.epam.cinema.exception.UserNotFoundException;
import com.epam.cinema.model.User;
import com.epam.cinema.model.impl.UserModel;
import com.epam.cinema.repository.UserRepository;
import com.epam.cinema.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserModel getById(long id) {
        log.info("find user by id {} ", id);
        UserModel user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("user was not found, id " + id));
        log.info("user with id {} was found", id);
        return user;
    }

    @Override
    @Transactional
    public UserModel create(UserModel user) {
        log.info("creating User");
        if (userRepository.existsById(user.getId())) {
            throw new UserAlreadyExistException("User with {} id: is already exist" + user.getId());
        }
        UserModel persistedUser = userRepository.save(user);
        log.info("User was created");
        return persistedUser;
    }

    @Override
    @Transactional
    public UserModel updateById(long id, UserModel userDto) {
        log.info("updating User");
        UserModel persistedUser = userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("user with id {} does not exist" + id));
        UserModel updatedUser = mapPresentUserFieldsUserDtoFields(persistedUser, userDto);
        log.info("user was updated");
        return updatedUser;
    }

    private UserModel mapPresentUserFieldsUserDtoFields(UserModel persistedUser, User userDto) {
        log.info("Started updating user ");
        String email = userDto.getEmail();
        if (Objects.nonNull(email)) {
            persistedUser.setEmail(email);
        }
        String name = userDto.getName();
        if (Objects.nonNull(name)) {
            persistedUser.setName(name);
        }
        log.info("User fields updated");
        return persistedUser;
    }

    @Override
    @Transactional
    public boolean deleteById(long id) {
        log.info("deleteUser with id {}", id);
        UserModel persistedUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("user not exist it could not be deleted"));
        userRepository.delete(persistedUser);
        if (!userRepository.existsById(id)) {
            log.info("user with id {} was deleted successfully", id);
            return true;
        } else {
            log.info("user with id {} was not deleted ", id);
            return false;
        }
    }

    @Override
    public UserModel getUserByEmail(String email) {
        log.info("Finding user by email");
        UserModel persistedUser = userRepository
                .getUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("user with email {} was not found" + email));
        log.info("User with  email {} was found: " + email);
        return persistedUser;
    }

    @Override
    public List<UserModel> getUsersByName(String name, int pageSize, int pageNum) {
       log.info("get users by name");
        Pageable pageable = (Pageable) PageRequest.of(pageSize,pageNum);
        List<UserModel> usersByName = userRepository.getUsersByName(name);
        log.info("users by name {} were found", name);
        return usersByName;
    }
}
