package org.example.sem_3_eksamen_programmering_lukas.controller;

import org.example.sem_3_eksamen_programmering_lukas.model.Album;
import org.example.sem_3_eksamen_programmering_lukas.model.Reservation;
import org.example.sem_3_eksamen_programmering_lukas.repository.AlbumRepository;
import org.example.sem_3_eksamen_programmering_lukas.repository.CustomerRepository;
import org.example.sem_3_eksamen_programmering_lukas.repository.ReservationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ReservationRestController.class)
class ReservationOnlyAvailableFilterTest {

    @Autowired MockMvc mvc;
    @MockitoBean ReservationRepository reservations;
    @MockitoBean CustomerRepository customers;
    @MockitoBean AlbumRepository albums;

    @Test
    void onlyAvailable_true_returnsOnlyReservationsWhoseAlbumsAreAvailable() throws Exception {
        Album avail = new Album();
        avail.setId(10);
        avail.setTitle("Available Album");
        avail.setArtist("Artist X");
        avail.setAvailable(true);

        Album notAvail = new Album();
        notAvail.setId(11);
        notAvail.setTitle("Unavailable Album");
        notAvail.setArtist("Artist Y");
        notAvail.setAvailable(false);

        Reservation r1 = new Reservation();
        r1.setActive(true);
        r1.setAlbum(avail);

        Reservation r2 = new Reservation();
        r2.setActive(true);
        r2.setAlbum(notAvail);

        // Controller path for onlyAvailable=true calls this repository method:
        Mockito.when(reservations.findByCustomerIdAndActiveTrue(1))
                .thenReturn(List.of(r1, r2));

        mvc.perform(get("/reservations/customer/1")
                        .param("onlyAvailable", "true"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].album.id", is(10)))
                .andExpect(jsonPath("$[0].album.available", is(true)));
    }
}
