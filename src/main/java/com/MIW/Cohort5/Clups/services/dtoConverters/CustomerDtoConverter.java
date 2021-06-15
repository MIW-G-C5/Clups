package com.MIW.Cohort5.Clups.services.dtoConverters;

import com.MIW.Cohort5.Clups.dtos.CustomerDto;
import com.MIW.Cohort5.Clups.model.Customer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kimberley Hommes - k.hommes@st.hanze.nl
 *
 * Converts between Model and Dto objects for Customer, so each type has only the necessary information
 */
public class CustomerDtoConverter {

    public List<CustomerDto> toCustomerDtos(List<Customer> models) {
        List<CustomerDto> result = new ArrayList<>();

        for (Customer model : models) {
            CustomerDto dto = toDto(model);
            result.add(dto);
        }

        return result;
    }

    public CustomerDto toDto(Customer model) {
        CustomerDto result = new CustomerDto();
        result.setCustomerCode(model.getCustomerCode());
        result.setFirstName(model.getFirstName());
        result.setInfixName(model.getInfixName());
        result.setLastName(model.getLastName());
        result.setPrepaidBalance(model.getPrepaidBalance());

        return result;
    }
}
