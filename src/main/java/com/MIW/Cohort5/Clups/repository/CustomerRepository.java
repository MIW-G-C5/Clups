package com.MIW.Cohort5.Clups.repository;

import com.MIW.Cohort5.Clups.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
