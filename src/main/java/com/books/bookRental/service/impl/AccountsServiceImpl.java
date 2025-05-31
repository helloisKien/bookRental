package com.books.bookRental.service.impl;


import com.books.bookRental.constants.AccountsConstants;
import com.books.bookRental.dto.AccountsDto;
import com.books.bookRental.dto.CustomerDto;
import com.books.bookRental.entity.Accounts;
import com.books.bookRental.entity.Customer;
import com.books.bookRental.exception.CustomerAlreadyExistsException;
import com.books.bookRental.exception.ResourceNotFoundException;
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
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService{

    private final CustomerRepository customerRepository;
    private final AccountsRepository accountsRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already registered with mobile number: " + customerDto.getMobileNumber());
        }
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("anonymous");

        Customer savedCustomer = customerRepository.save(customer);

        Accounts account = createNewAccount(savedCustomer);
        accountsRepository.save(account);
    }

    private Accounts createNewAccount(Customer customer) {
        Accounts account = new Accounts();
        account.setCustomerId(customer.getCustomerId());
        account.setAccountNumber(generateRandomAccountNumber());
        account.setAccountType("SAVINGS");
        account.setBranchAddress("123 Main Street, New York");
        account.setCreatedAt(LocalDateTime.now());
        account.setCreatedBy("anonymous");
        return account;
    }

    private long generateRandomAccountNumber() {
        return 1000000000L + new Random().nextInt(900000000);
    }

    @Override
    public CustomerDto getCustomerFromMobileNumber(String mobileNumber){

        Optional<Customer> customerFromMobileNumber = customerRepository.findByMobileNumber(mobileNumber);
        if(customerFromMobileNumber.isEmpty()){
            throw new ResourceNotFoundException("Customer","mobileNumber",mobileNumber);
        }
        Customer customer = customerFromMobileNumber.get();
        Optional<Accounts> accountsFromCustomerId = accountsRepository.findAccountsByCustomerId(customer.getCustomerId());
        if(accountsFromCustomerId.isEmpty()){
            throw new ResourceNotFoundException("Accounts","customerId",String.valueOf(customer.getCustomerId()));
        }
        Accounts accounts = accountsFromCustomerId.get();
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer,new CustomerDto());
        AccountsDto accountsDto = AccountsMapper.mapToAccountsDto(accounts,new AccountsDto());
        customerDto.setAccountsDto(accountsDto);
        return customerDto;

    }
}
