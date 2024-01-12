package space.jbp.ch18_rest_ju5.beans;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import space.jbp.ch18_rest_ju5.model.Country;
import space.jbp.ch18_rest_ju5.model.Flight;
import space.jbp.ch18_rest_ju5.model.Passenger;

@Configuration
public class FlightBuilder {
  private Map<String, Country> map = new HashMap<>();

  public FlightBuilder() throws IOException {
    try (BufferedReader br = new BufferedReader(
        new FileReader("src/main/resources/countries.csv"))) {
      String line;
      while ((line = br.readLine()) != null) {
        String items[] = line.split(",");
        Country country = new Country(items[0].trim(), items[1].trim());
        map.put(items[1].trim(), country);
      }
    }
  }

  @Bean
  Map<String, Country> getCountryMap() {
    return Collections.unmodifiableMap(map);
  }

  @Bean
  Flight buildFlight() throws IOException {
    Flight flight = new Flight("KR007", 10);

    try (BufferedReader br = new BufferedReader(
        new FileReader("src/test/resources/passengers.csv"))) {
      String line;
      while ((line = br.readLine()) != null) {
        String items[] = line.split(",");
        flight.addPassenger(
            new Passenger(items[0].trim(), map.get(items[1].trim())));
      }
    }
    return flight;
  }
}
