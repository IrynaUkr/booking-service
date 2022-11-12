package com.epam.cinema.service;

import com.epam.cinema.model.User;
import com.epam.cinema.model.impl.UserModel;

import java.util.List;

public interface UserService extends CrudService<UserModel>{
    UserModel getUserByEmail(String email);

    List<UserModel> getUsersByName(String name, int pageSize, int pageNum);
}
