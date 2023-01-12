package com.va.simple.service;
import com.va.simple.dto.AccountDto;
import com.va.simple.dto.AccountDtoConverter;
import com.va.simple.dto.CreateAccountRequest;
import com.va.simple.model.Account;
import com.va.simple.model.Customer;
import com.va.simple.model.Transaction;
import com.va.simple.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final CustomerService customerService;
    private final AccountDtoConverter converter;

    public AccountService(AccountRepository accountRepository,
                          CustomerService customerService,
                          AccountDtoConverter converter) {
        this.accountRepository = accountRepository;
        this.customerService = customerService;
        this.converter = converter;
    }

    public AccountDto createAccount(CreateAccountRequest createAccountRequest){
        Customer customer = customerService.findCustomerById(createAccountRequest.getCustomerId());
        Account account = new Account(customer, createAccountRequest.getInitialCredit());

        if(createAccountRequest.getInitialCredit().compareTo(BigDecimal.ZERO)>0){
            Transaction transaction =new Transaction(createAccountRequest.getInitialCredit(), account);
            account.getTransaction().add(transaction);
        }
        return converter.convert(accountRepository.save(account));
    }
}
