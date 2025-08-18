package org.example.sem_3_eksamen_programmering_lukas.controller;

import org.example.sem_3_eksamen_programmering_lukas.model.Album;
import org.example.sem_3_eksamen_programmering_lukas.repository.AlbumRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/placeholder")
public class PlaceholderController {
    private final AlbumRepository repository;
    public PlaceholderController(AlbumRepository repository) {
        this.repository = repository;
    }

    @GetMapping public List<Album> getAll() {
        return repository.findAll();
    }

    @PostMapping public Album add(@RequestBody Album album) {
        return repository.save(album);
    }
}
