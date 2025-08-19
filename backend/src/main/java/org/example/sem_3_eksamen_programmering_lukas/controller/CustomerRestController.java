package org.example.sem_3_eksamen_programmering_lukas.controller;

import org.example.sem_3_eksamen_programmering_lukas.model.Customer;
import org.example.sem_3_eksamen_programmering_lukas.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerRestController {
    @Autowired CustomerRepository customers;

    @GetMapping("/{id}")
    public Customer get(@PathVariable int id) {
        return customers.findById(id).orElseThrow();
    }

    @GetMapping("/search")
    public List<Customer> search(@RequestParam(name = "q", defaultValue = "") String q) {
        if (q.isBlank()) return customers.findAll();
        return customers.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(q, q);
    }
}
