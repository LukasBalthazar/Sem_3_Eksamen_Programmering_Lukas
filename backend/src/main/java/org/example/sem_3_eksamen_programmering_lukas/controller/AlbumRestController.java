package org.example.sem_3_eksamen_programmering_lukas.controller;

import org.example.sem_3_eksamen_programmering_lukas.model.Album;
import org.example.sem_3_eksamen_programmering_lukas.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/index/albums")
public class AlbumRestController {
    @Autowired AlbumRepository albums;
    //@Autowired StoreRepository;

    @GetMapping("/")
    public List<Album> getAllAlbums(){
        return albums.findAll();
    }

    @GetMapping("/{id}")
    public Album getAlbumById(@PathVariable int id){
        return albums.findById(id).get();
    }

    @PostMapping("/")
    public ResponseEntity<Album> createAlbum(@RequestBody Album a) {
        Album saved = albums.save(a);
        return ResponseEntity.created(URI.create("/albums/" + saved.getId())).body(saved);
    }

}
