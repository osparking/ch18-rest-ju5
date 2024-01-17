package space.jbp.ch18_rest_ju5.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import space.jbp.ch18_rest_ju5.beans.FlightBuilder;
import space.jbp.ch18_rest_ju5.model.Country;
import space.jbp.ch18_rest_ju5.model.CountryRepository;

@SpringBootTest
@AutoConfigureMockMvc
class RestAppTest {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private Map<String, Country> countryMap;

  @MockBean
  private CountryRepository countryRepo;

  @Test
  void testGetCountries() {
    when(countryRepo.findAll())
        .thenReturn(new ArrayList<>(countryMap.values()));
    mvc.perform(get("/countries"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$", hasSize(3)));
  }
}
