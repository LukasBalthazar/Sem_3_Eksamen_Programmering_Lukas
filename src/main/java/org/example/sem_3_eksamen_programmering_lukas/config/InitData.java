package org.example.sem_3_eksamen_programmering_lukas.config;

import org.example.sem_3_eksamen_programmering_lukas.model.Album;
import org.example.sem_3_eksamen_programmering_lukas.model.Customer;
import org.example.sem_3_eksamen_programmering_lukas.repository.AlbumRepository;
import org.example.sem_3_eksamen_programmering_lukas.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
@Component
public class InitData implements CommandLineRunner {

    @Autowired
    AlbumRepository albums;
    @Autowired
    CustomerRepository customers;

    @Override
    public void run(String... args) throws Exception {

        Album a1 = new Album();
        a1.setTitle("For Ever");
        a1.setArtist("Jungle");
        a1.setAvailable(true);
        albums.save(a1);

        Album a2 = new Album();
        a2.setTitle("In The Moment");
        a2.setArtist("Kaskade");
        a2.setAvailable(false);
        albums.save(a2);

        Album a3 = new Album();
        a3.setTitle("Discovery");
        a3.setArtist("Daft Punk");
        a3.setAvailable(true);
        albums.save(a3);


        Customer c1 = new Customer();
        c1.setName("Alice");
        c1.setEmail("alice@example.com");
        customers.save(c1);

        Customer c2 = new Customer();
        c2.setName("Bob");
        c2.setEmail("bob@example.com");
        customers.save(c2);
    }

}

