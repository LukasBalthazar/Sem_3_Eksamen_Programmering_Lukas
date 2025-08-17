package org.example.sem_3_eksamen_programmering_lukas.controller;

import org.example.sem_3_eksamen_programmering_lukas.model.Placeholder;
import org.example.sem_3_eksamen_programmering_lukas.repository.PlaceholderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/placeholder")
public class PlaceholderController {
    private final PlaceholderRepository repository;
    public PlaceholderController(PlaceholderRepository repository) {
        this.repository = repository;
    }

    @GetMapping public List<Placeholder> getAll() {
        return repository.findAll();
    }

    @PostMapping public Placeholder add(@RequestBody Placeholder placeholder) {
        return repository.save(placeholder);
    }
}
