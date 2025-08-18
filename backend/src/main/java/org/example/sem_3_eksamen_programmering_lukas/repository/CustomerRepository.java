package org.example.sem_3_eksamen_programmering_lukas.repository;

import org.example.sem_3_eksamen_programmering_lukas.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByCustomerEmail(String email);
}
