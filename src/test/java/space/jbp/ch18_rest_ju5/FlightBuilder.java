package space.jbp.ch18_rest_ju5;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import space.jbp.ch18_rest_ju5.model.Country;
import space.jbp.ch18_rest_ju5.model.Flight;
import space.jbp.ch18_rest_ju5.model.Passenger;

@TestConfiguration
public class FlightBuilder {
  private static Map<String, Country> map = new HashMap<>();
  static {
    map.put("KR", new Country("대한민국", "KR"));
    map.put("JP", new Country("일본", "JP"));
    map.put("CN", new Country("중국", "CN"));
    map.put("AU", new Country("호주", "AU"));
    map.put("US", new Country("미국", "US"));
    map.put("UK", new Country("영국", "UK"));
  }
  
  @Bean
  Flight buildFlight() throws FileNotFoundException, IOException {
    Flight flight    = new Flight("KR007", 10);
    
    /**
     * csv 파일을 읽거 이로부터 Passenger 목록을 만든 뒤 반환한다.
     */
    Collection<Passenger> passengers = new ArrayList<>();
    
    try (BufferedReader br = new BufferedReader(
        new FileReader("src/test/resources/passengers.csv"))) {
      int i = 1;
      String line;
      while ((line = br.readLine()) != null) {
        String items[] = line.split(",");
        passengers.add(new Passenger(items[0].trim(), map.get(items[1].trim())));
      }
      flight.setPassengers(passengers);
    }
    return flight;
  }

}
