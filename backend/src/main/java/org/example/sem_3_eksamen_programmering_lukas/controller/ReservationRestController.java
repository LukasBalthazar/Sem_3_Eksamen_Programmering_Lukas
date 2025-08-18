package org.example.sem_3_eksamen_programmering_lukas.controller;

import org.example.sem_3_eksamen_programmering_lukas.model.Reservation;
import org.example.sem_3_eksamen_programmering_lukas.repository.AlbumRepository;
import org.example.sem_3_eksamen_programmering_lukas.repository.CustomerRepository;
import org.example.sem_3_eksamen_programmering_lukas.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationRestController {
    @Autowired
    ReservationRepository reservations;
    @Autowired
    CustomerRepository customers;
    @Autowired
    AlbumRepository albums;

    @PostMapping("/register")
    public Reservation register(@RequestParam int customerId, @RequestParam int albumId) {
        Reservation r = reservations.findByCustomerIdAndAlbumId(customerId, albumId)
                .orElseGet(() -> {
                    Reservation nr = new Reservation();
                    nr.setCustomer(customers.findById(customerId).orElseThrow());
                    nr.setAlbum(albums.findById(albumId).orElseThrow());
                    return nr;});

                r.setActive(true);
                return reservations.save(r);
    }

    @PostMapping("/unregister")
    public ResponseEntity<Void> unregister(@RequestParam int customerId, @RequestParam int albumId){
        Reservation r = reservations.findByCustomerIdAndAlbumId(customerId, albumId).orElseThrow();
        r.setActive(false);
        reservations.save(r);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/customer/{customerId}")
    public List<Reservation> list(@PathVariable int customerId, @RequestParam(defaultValue="false") boolean onlyAvailable){
        if (onlyAvailable) {
            // active reservations where album.available = true
            return reservations.findByCustomerIdAndActiveTrue(customerId)
                    .stream().filter(r -> r.getAlbum()!=null && r.getAlbum().isAvailable()).toList();
        }
        return reservations.findByCustomerId(customerId);
    }



}
