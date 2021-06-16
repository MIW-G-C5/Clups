package com.MIW.Cohort5.Clups.services;

import com.MIW.Cohort5.Clups.dtos.CustomerDto;
import com.MIW.Cohort5.Clups.model.Customer;

import java.util.List;

public interface CustomerService {

    List<CustomerDto> getAll();

    void saveCustomer(CustomerDto customerDto);

    Customer addNew(Customer customer);

    int getHighestCustomerCode();
}
