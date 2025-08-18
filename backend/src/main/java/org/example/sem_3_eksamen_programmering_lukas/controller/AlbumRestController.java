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

    //List
    @GetMapping("/")
    public List<Album> getAllAlbums(){
        return albums.findAll();
    }

    //Get
    @GetMapping("/{id}")
    public Album getAlbumById(@PathVariable int id){
        return albums.findById(id).get();
    }

    //Create
    @PostMapping("/")
    public ResponseEntity<Album> createAlbum(@RequestBody Album a) {
        Album saved = albums.save(a);
        return ResponseEntity.created(URI.create("/albums/" + saved.getId())).body(saved);
    }

    //Update
    @PutMapping("/{id}")
    public Album updateAlbum(@PathVariable int id, @RequestBody Album in) {
        Album a = albums.findById(id).get();
        a.setTitle(in.getTitle());
        a.setArtist(in.getArtist());
        /*a.setAvailable(in.isAvailable());
        if (in.getStore() != null) {
            int sid = in.getStore().getId();
            a.setStore(albums.findById(sid).orElseThrow());
        }*/
        return albums.save(a);
    }

    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable int id) {
        albums.deleteById(id);
        return ResponseEntity.noContent().build();
    }
/*
    // Assign album to store (Opgave-4A)
    @PostMapping("/{albumId}/assign-store/{storeId}")
    public Album assign(@PathVariable int albumId, @PathVariable int storeId){
        Album a = albums.findById(albumId).orElseThrow();
        a.setStore(stores.findById(storeId).orElseThrow());
        return albums.save(a);
    }

 */
}
