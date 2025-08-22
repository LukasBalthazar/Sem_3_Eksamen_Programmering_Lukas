package org.example.sem_3_eksamen_programmering_lukas.repository;

import org.example.sem_3_eksamen_programmering_lukas.model.Album;
import org.example.sem_3_eksamen_programmering_lukas.model.Customer;
import org.example.sem_3_eksamen_programmering_lukas.model.Reservation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ReservationRepositoryTest {

    @Autowired ReservationRepository reservations;
    @Autowired CustomerRepository customers;
    @Autowired AlbumRepository albums;

    @Test
    void findByCustomerId_returnsAllReservationsForCustomer() {
        Customer c1 = new Customer(); c1.setName("Alice"); c1.setEmail("alice@example.com");
        c1 = customers.save(c1);
        final int c1Id = c1.getId(); // <-- capture once, use in lambdas/assertions

        Customer c2 = new Customer(); c2.setName("Bob"); c2.setEmail("bob@example.com");
        c2 = customers.save(c2);

        Album a1 = new Album(); a1.setTitle("T1"); a1.setArtist("A1"); a1.setAvailable(true);
        a1 = albums.save(a1);
        Album a2 = new Album(); a2.setTitle("T2"); a2.setArtist("A2"); a2.setAvailable(false);
        a2 = albums.save(a2);

        Reservation r1 = new Reservation(); r1.setCustomer(c1); r1.setAlbum(a1); r1.setActive(true);  reservations.save(r1);
        Reservation r2 = new Reservation(); r2.setCustomer(c1); r2.setAlbum(a2); r2.setActive(false); reservations.save(r2);
        Reservation r3 = new Reservation(); r3.setCustomer(c2); r3.setAlbum(a1); r3.setActive(true);  reservations.save(r3);

        var allForC1 = reservations.findByCustomerId(c1Id);
        assertThat(allForC1).hasSize(2);

        // Option A: use extracting (no lambda capture of c1)
        assertThat(allForC1)
                .extracting(r -> r.getCustomer().getId())
                .containsOnly(c1Id);

        // Option B: if you prefer allSatisfy, use c1Id (final) instead of c1
        // assertThat(allForC1).allSatisfy(r -> assertThat(r.getCustomer().getId()).isEqualTo(c1Id));
    }

    @Test
    void findByCustomerIdAndActiveTrue_returnsOnlyActive() {
        Customer c = new Customer(); c.setName("Alice"); c.setEmail("alice@example.com"); c = customers.save(c);
        Album a  = new Album(); a.setTitle("T1"); a.setArtist("A1"); a.setAvailable(true); a = albums.save(a);

        Reservation active = new Reservation(); active.setCustomer(c); active.setAlbum(a); active.setActive(true);  reservations.save(active);
        Reservation inactive = new Reservation(); inactive.setCustomer(c); inactive.setAlbum(a); inactive.setActive(false); reservations.save(inactive);

        var result = reservations.findByCustomerIdAndActiveTrue(c.getId());
        assertThat(result).hasSize(1);
        assertThat(result.get(0).isActive()).isTrue();
    }

    @Test
    void findByCustomerIdAndAlbumId_returnsTheReservationIfAny() {
        Customer c = new Customer(); c.setName("Alice"); c.setEmail("alice@example.com"); c = customers.save(c);
        Album a  = new Album(); a.setTitle("T1"); a.setArtist("A1"); a.setAvailable(true); a = albums.save(a);

        Reservation r = new Reservation(); r.setCustomer(c); r.setAlbum(a); r.setActive(true); reservations.save(r);

        var found = reservations.findByCustomerIdAndAlbumId(c.getId(), a.getId());
        assertThat(found).isPresent();
        assertThat(found.get().getCustomer().getId()).isEqualTo(c.getId());
        assertThat(found.get().getAlbum().getId()).isEqualTo(a.getId());
    }
}
