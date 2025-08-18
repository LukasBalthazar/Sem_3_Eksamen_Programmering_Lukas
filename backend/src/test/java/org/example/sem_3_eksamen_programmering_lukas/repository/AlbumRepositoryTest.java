// src/test/java/.../AlbumRepositoryTest.java
package org.example.sem_3_eksamen_programmering_lukas.repository;

import org.example.sem_3_eksamen_programmering_lukas.model.Album;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AlbumRepositoryTest {
    @Autowired AlbumRepository albums;

    @Test void createAndFind() {
        Album a = new Album();
        a.setTitle("Kind of Blue");
        a.setArtist("Miles Davis");
        a.setAvailable(true);
        albums.save(a);

        var all = albums.findAll();
        assertThat(all).isNotEmpty();
        assertThat(all).extracting(Album::getTitle).contains("Kind of Blue");
    }


}
