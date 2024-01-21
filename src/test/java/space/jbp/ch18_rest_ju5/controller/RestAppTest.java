package space.jbp.ch18_rest_ju5.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import space.jbp.ch18_rest_ju5.exception.NoPassengerException;
import space.jbp.ch18_rest_ju5.model.Country;
import space.jbp.ch18_rest_ju5.model.CountryRepository;
import space.jbp.ch18_rest_ju5.model.Flight;
import space.jbp.ch18_rest_ju5.model.Passenger;
import space.jbp.ch18_rest_ju5.model.PassengerRepository;

@SpringBootTest
@AutoConfigureMockMvc
class RestAppTest {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private Map<String, Country> countryMap;

  @MockBean
  private CountryRepository countryRepo;

  @MockBean
  private PassengerRepository passengerRepo;

  @Autowired
  private Flight flight;

  @Test
  void testPatchPassenger() throws Exception {
    // 승객 생성
    Country aCountry = countryMap.get("US");
    Passenger passenger = new Passenger("이구름", aCountry);
    passenger.setRegistered(false);
    when(passengerRepo.findById(2L)).thenReturn(Optional.of(passenger));
    when(passengerRepo.save(passenger)).thenReturn(passenger);
    String update = 
        "{\"name\":\"이구름\", \"registered\": \"true\", \"country\": \"CN\"}";
    mvc.perform(patch("/passengers/2").content(update)
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    verify(passengerRepo, times(1)).save(passenger);
    verify(passengerRepo, times(1)).findById(2L);
    assertTrue(passengerRepo.findById(2L).get().isRegistered());
  }

  @Test
  void testPostPassenger() throws Exception {
    Passenger passenger = new Passenger("박철수", countryMap.get("KR"));
    passenger.setRegistered(false);
    when(passengerRepo.save(passenger)).thenReturn(passenger);

    mvc.perform(post("/passengers")
        .content(new ObjectMapper().writeValueAsString(passenger))
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.name").value("박철수"))
        .andExpect(jsonPath("$.country.countryCode", is("KR")))
        .andExpect(jsonPath("$.country.name", is("대한민국")))
        .andExpect(jsonPath("$.registered", is(Boolean.FALSE)));
    verify(passengerRepo, times(2)).save(passenger);
  }

  @Test
  void testNoPassenger() {
    Throwable throwable = assertThrows(ServletException.class, () -> mvc
        .perform(get("/passengers/30")).andExpect(status().isNotFound()));
    assertEquals(NoPassengerException.class, throwable.getCause().getClass());
  }

  @Test
  void testGetCountries() throws Exception {
    when(countryRepo.findAll())
        .thenReturn(new ArrayList<>(countryMap.values()));
    mvc.perform(get("/countries")).andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$", hasSize(6)));
    verify(countryRepo, times(1)).findAll();
  }

  // @formatter:off
  @Test
  void testGetAllPassengers() throws Exception {
    when(passengerRepo.findAll())
        .thenReturn(new ArrayList<>(flight.getPassengers()));
    mvc.perform(get("/passengers"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$", hasSize(10)));
    verify(passengerRepo, times(1)).findAll();
  }
  // @formatter:on
}
