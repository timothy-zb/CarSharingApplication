package com.carsharingapp.repository;

import com.carsharingapp.domain.Location;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LocationRepositoryTest {

    @Autowired
    LocationRepository repository;

    @Test
    public void testSaveLocation() {
        Location location = new Location();
        location.setLatitude(44.4513003);
        location.setLongitude(26.0415585);
        location.setAddress("aleea lacul morii nr. 4");

        Location dbLocation = repository.saveAndFlush(location);

        Assert.assertNotNull(dbLocation.getId());
    }

    @Test
    public void testFindLocationById() {
        Location location = new Location();
        location.setLatitude(44.4513003);
        location.setLongitude(26.0415585);
        location.setAddress("Crangasi");

        Location dbLocation = repository.save(location);

        Optional<Location> fromFind = repository.findById(dbLocation.getId());
        Assert.assertTrue(fromFind.isPresent());
        Assert.assertEquals(dbLocation.getId(), fromFind.get().getId());
    }
}
