package com.epam.cinema.service.impl;

import com.epam.cinema.exception.UserAccountAlreadyExistException;
import com.epam.cinema.exception.UserAccountNotFoundException;
import com.epam.cinema.model.impl.UserAccount;
import com.epam.cinema.model.impl.UserModel;
import com.epam.cinema.repository.UserAccountRepository;
import com.epam.cinema.repository.UserRepository;
import com.epam.cinema.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository accountRepository;

    private final UserRepository userRepository;
    private final UserServiceImpl userService;

    @Override
    public UserAccount getById(long id) {
        log.info("find user account by id {}", id);
        UserAccount account = accountRepository
                .findById(id)
                .orElseThrow(() -> new UserAccountNotFoundException("account  not found"));
        log.info("user account with id {} was found", id);
        return account;
    }

    @Override
    @Transactional
    public UserAccount create(UserAccount account) {
        log.info("creating User Account");

        if (accountRepository.existsById(account.getId())) {
            throw new UserAccountAlreadyExistException("account already exist");
        }

        UserModel persistedUser = userService.getById(account.getUser().getId());
        account.setUser(persistedUser);
        UserAccount persistedAccount = accountRepository.save(account);
        log.info("User account was created");
        return persistedAccount;
    }

    @Override
    @Transactional
    public UserAccount updateById(long id, UserAccount accountDto) {
        log.info("updating User Account");
        UserAccount account = accountRepository
                .findById(id)
                .orElseThrow(() -> new UserAccountNotFoundException("user account not found, id" + id));
        account.setBalance(accountDto.getBalance());
        accountRepository.save(account);
        log.info("user account balance was updated");
        return account;
    }

    @Override
    public boolean deleteById(long id) {
        return false;
    }


    @Override
    public Double refillAccount(UserAccount account, Double amount) {
        log.info("refilling User Account");
        UserAccount persistedAccount = getById(account.getId());
        Double refilledBalance = persistedAccount.getBalance() + amount;
        persistedAccount.setBalance(refilledBalance);
        UserAccount save = accountRepository.save(persistedAccount);
        log.info("user account balance was  refilled");
        return save.getBalance();
    }
}
