package com.adfer.repository;

import com.adfer.domain.Customer;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by adrianferenc on 10.10.2016.
 */
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    Customer findByFirstNameAndLastName(String firstName, String lastName);
}
