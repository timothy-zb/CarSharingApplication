package com.carsharingapp.rest;


import com.carsharingapp.domain.Location;
import com.carsharingapp.repository.LocationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static java.util.Arrays.asList;

@RunWith(SpringRunner.class)
@WebMvcTest
public class LocationControllerMockTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    LocationRepository repository;

    @Test
    public void getLocations() throws Exception {
        Location a = new Location();
        a.setId(1L);
        a.setLatitude(44.4513003);
        a.setLongitude(26.0415585);
        a.setAddress("Crangasi");

        Mockito.when(repository.findAll())
                .thenReturn(asList(a));

        mvc.perform(MockMvcRequestBuilders.get("/locations/")
                                            .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                                        .string("[{" +
                                        "\"id\":1," +
                                        "\"latitude\":44.4513003," +
                                        "\"longitude\":26.0415585," +
                                        "\"address\":\"Crangasi\"," +
                                        "\"city\":\"Bucuresti\"," +
                                        "\"state\":\"B\"," +
                                        "\"zip\":\"123-123\"" +
                                        "}]"))
                .andDo(MockMvcResultHandlers.print());

        Mockito.verify(repository).findAll();
    }
}
