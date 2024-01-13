package space.jbp.ch18_rest_ju5;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import space.jbp.ch18_rest_ju5.beans.FlightBuilder;
import space.jbp.ch18_rest_ju5.model.Country;
import space.jbp.ch18_rest_ju5.model.CountryRepository;

@SpringBootApplication
@Import(FlightBuilder.class)
public class Ch18RestJu5Application {

  @Autowired
  private Map<String, Country> countriesMap;

  public static void main(String[] args) {
    SpringApplication.run(Ch18RestJu5Application.class, args);
  }

  @Bean
  CommandLineRunner configureRepository(CountryRepository repository) {
    return args -> {
      for (Country country : countriesMap.values()) {
        repository.save(country);
      }
    };
  }
}
