package com.example.demo;

import com.example.demo.domain.Location;
import com.example.demo.domain.User;
import com.example.demo.repository.LocationRepository;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class LocationRepositoryTest extends DemoApplicationTests{

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void create(){

        double latitude = 35.12345;
        double longitude = 113.12345;

        User user = userRepository.getUserById("1539045321");

        Location location = new Location();
        location.setUser(user);
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        location.setCreated_at(new Timestamp(new Date().getTime()));

        Location newLocation = locationRepository.save(location);

        List<Location> locations =locationRepository.getLocationsByUserIdAndCreated_atEquals("1539045321", new Timestamp(new Date().getTime()));
        System.out.println(locations.toString());
    }
}
