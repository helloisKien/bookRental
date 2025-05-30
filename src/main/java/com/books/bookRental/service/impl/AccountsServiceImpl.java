package com.books.bookRental.service.impl;


import com.books.bookRental.constants.AccountsConstants;
import com.books.bookRental.dto.AccountsDto;
import com.books.bookRental.dto.CustomerDto;
import com.books.bookRental.entity.Accounts;
import com.books.bookRental.entity.Customer;
import com.books.bookRental.exception.CustomerAlreadyExistsException;
import com.books.bookRental.mapper.AccountsMapper;
import com.books.bookRental.mapper.CustomerMapper;
import com.books.bookRental.repository.AccountsRepository;
import com.books.bookRental.repository.CustomerRepository;
import com.books.bookRental.service.IAccountsService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class AccountsServiceImpl  implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    /**
     * @param customerDto - CustomerDto Object
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
        if(customerRepository.existsByMobileNumber(customerDto.getMobileNumber())){
            throw new CustomerAlreadyExistsException(AccountsConstants.CUSTOMER_ALREADY_EXISTS);
        } // check if email already exists in database or not
        CustomerMapper customerMapper = new CustomerMapper();
        Customer customerFromDto = customerMapper.mapToCustomer(customerDto, new Customer());
        customerFromDto.setCreatedAt(LocalDateTime.now());
        customerFromDto.setCreatedBy(customerDto.getName());
        Customer savedCustomer = customerRepository.save(customerFromDto);
        Accounts accounts = new Accounts(savedCustomer.getCustomerId(), savedCustomer.getCustomerId(), "Type 1", "Branch 1");
        accountsRepository.save(accounts);




    }
}
