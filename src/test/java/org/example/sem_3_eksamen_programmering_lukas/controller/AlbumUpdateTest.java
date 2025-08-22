package org.example.sem_3_eksamen_programmering_lukas.controller;

import org.example.sem_3_eksamen_programmering_lukas.model.Album;
import org.example.sem_3_eksamen_programmering_lukas.repository.AlbumRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AlbumRestController.class)
class AlbumUpdateTest {

    @Autowired MockMvc mvc;
    @MockitoBean AlbumRepository albums;

    @Test
    void put_updates_title_artist_available() throws Exception {
        Album existing = new Album();
        existing.setId(5);
        existing.setTitle("Old Title");
        existing.setArtist("Old Artist");
        existing.setAvailable(false);

        Mockito.when(albums.findById(5)).thenReturn(Optional.of(existing));
        // Echo back the mutated object as "saved"
        Mockito.when(albums.save(Mockito.any(Album.class)))
                .thenAnswer(inv -> inv.getArgument(0));

        mvc.perform(put("/albums/5")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"New T\",\"artist\":\"New A\",\"available\":true}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(5)))
                .andExpect(jsonPath("$.title", is("New T")))
                .andExpect(jsonPath("$.artist", is("New A")))
                .andExpect(jsonPath("$.available", is(true)));
    }
}
