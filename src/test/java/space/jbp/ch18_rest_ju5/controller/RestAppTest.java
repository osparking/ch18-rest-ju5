package space.jbp.ch18_rest_ju5.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import jakarta.servlet.ServletException;
import space.jbp.ch18_rest_ju5.exception.NoPassengerException;
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
}
