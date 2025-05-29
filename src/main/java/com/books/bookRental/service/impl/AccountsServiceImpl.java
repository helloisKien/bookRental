package com.books.bookRental.service.impl;


import com.books.bookRental.constants.AccountsConstants;
import com.books.bookRental.dto.AccountsDto;
import com.books.bookRental.dto.CustomerDto;
import com.books.bookRental.entity.Accounts;
import com.books.bookRental.entity.Customer;
import com.books.bookRental.mapper.AccountsMapper;
import com.books.bookRental.mapper.CustomerMapper;
import com.books.bookRental.repository.AccountsRepository;
import com.books.bookRental.repository.CustomerRepository;
import com.books.bookRental.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl  implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    /**
     * @param customerDto - CustomerDto Object
     */
    @Override
    public void createAccount(CustomerDto customerDto) {

    }
}
