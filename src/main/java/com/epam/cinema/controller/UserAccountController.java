package com.epam.cinema.controller;

import com.epam.cinema.model.impl.UserAccount;
import com.epam.cinema.service.impl.BookingFacadeImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserAccountController {

    private final BookingFacadeImpl bookingFacade;

    @PatchMapping("/account/{amount}")
    public String refillAccount(@PathVariable Double amount,
            @RequestBody UserAccount account, Model model){
        bookingFacade.refillAccountBalance(account, amount);
        model.addAttribute("account", account);
        return "account";
    }

    @PostMapping("/account")
    public String createAccount(@RequestBody UserAccount accountDto, Model model){
        UserAccount account = bookingFacade.createUserAccount(accountDto);
        model.addAttribute("account", account);
        return "account";
    }
}
