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
        Album a = new Album();
        a.setTitle("In Search of Sunrise");
        a.setArtist("Tiesto");
        a.setAvailable(true);
        //a.setStore(cph);
        albums.save(a);

        Customer u = new Customer();
        u.setName("Test User");
        u.setEmail("test@example.com");
        customers.save(u);

    }

}

