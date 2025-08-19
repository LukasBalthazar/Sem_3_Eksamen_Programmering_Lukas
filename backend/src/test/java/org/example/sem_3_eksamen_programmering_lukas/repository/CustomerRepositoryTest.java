package org.example.sem_3_eksamen_programmering_lukas.repository;

import org.example.sem_3_eksamen_programmering_lukas.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CustomerRepositoryTest {

    @Autowired CustomerRepository customers;

    @Test
    void searchByNameOrEmail_isCaseInsensitive_andMatchesEitherField() {
        Customer c1 = new Customer(); c1.setName("Alice Anderson"); c1.setEmail("alice@example.com");
        Customer c2 = new Customer(); c2.setName("Bob Builder");     c2.setEmail("bob@Example.com"); // mixed case on purpose
        Customer c3 = new Customer(); c3.setName("Charlie");         c3.setEmail("charlie@dev.local");
        customers.saveAll(List.of(c1, c2, c3));

        // search by name (case-insensitive)
        var byName = customers.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase("aliCe", "");
        assertThat(byName).extracting(Customer::getEmail).contains("alice@example.com");

        // search by email (case-insensitive)
        var byEmail = customers.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase("", "EXAMPLE.COM");
        assertThat(byEmail).extracting(Customer::getName).contains("Alice Anderson", "Bob Builder");
    }
}
