package com.epam.cinema.service;

import com.epam.cinema.model.impl.UserAccount;


public interface UserAccountService extends CrudService<UserAccount> {

    Double refillAccount(UserAccount account, Double amount);
}
