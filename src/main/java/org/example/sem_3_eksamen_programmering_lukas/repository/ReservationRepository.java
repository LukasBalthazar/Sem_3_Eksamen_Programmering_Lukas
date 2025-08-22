package org.example.sem_3_eksamen_programmering_lukas.repository;

import org.example.sem_3_eksamen_programmering_lukas.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    List<Reservation> findByCustomerId(int customerId);
    List<Reservation> findByCustomerIdAndActiveTrue(int customerId);
    Optional<Reservation> findByCustomerIdAndAlbumId(int customerId, int albumId);
}
