package com.MIW.Cohort5.Clups.services.implementations;

import com.MIW.Cohort5.Clups.dtos.CustomerDto;
import com.MIW.Cohort5.Clups.model.Customer;
import com.MIW.Cohort5.Clups.repository.CustomerRepository;
import com.MIW.Cohort5.Clups.services.CustomerService;
import com.MIW.Cohort5.Clups.services.dtoConverters.CustomerDtoConverter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kimberley Hommes - k.hommes@st.hanze.nl
 *
 * This service connects with the Customer repository
 */

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private CustomerDtoConverter dtoConverter;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.dtoConverter = new CustomerDtoConverter();
    }

    @Override
    public List<CustomerDto> getAll() {
        List<Customer> models = customerRepository.findAll();

        return dtoConverter.toCustomerDtos(models);
    }

    @Override
    public void saveCustomer(CustomerDto customerDto) {
        Customer newCustomer = dtoConverter.toModel(customerDto);

        addNew(newCustomer);
    }

    @Override
    public Customer addNew(Customer customer) {
        // if a customer does not yet have a customerCode (i.e. it does not yet exist in the database),
        // generate an new customerCode, both for use in the application and for seeder to (re)build database
        if(customer.getCustomerCode() <= 0) {
            customer.setCustomerCode(getHighestCustomerCode() + 1);
        }

        return customerRepository.save(customer);
    }

    @Override
    public int getHighestCustomerCode() {
        int customerCode = 0;

        List<Customer> allCustomers = customerRepository.findAll();

        for (Customer customer : allCustomers) {
            if(customer.getCustomerCode() > customerCode) {
                customerCode = customer.getCustomerCode();
            }
        }

        return customerCode;
    }
}
