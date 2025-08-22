package org.example.sem_3_eksamen_programmering_lukas.repository;

import org.example.sem_3_eksamen_programmering_lukas.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Integer> {
    List<Album> findByAvailableTrue();
    List<Album> findByArtist(String artist);
}
