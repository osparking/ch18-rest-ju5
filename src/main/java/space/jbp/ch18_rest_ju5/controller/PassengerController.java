package space.jbp.ch18_rest_ju5.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import space.jbp.ch18_rest_ju5.exception.NoPassengerException;
import space.jbp.ch18_rest_ju5.model.Country;
import space.jbp.ch18_rest_ju5.model.Passenger;
import space.jbp.ch18_rest_ju5.model.PassengerRepository;

@RestController
public class PassengerController {
  @Autowired
  private PassengerRepository repository;

  @Autowired
  private Map<String, Country> countryMap;

  @GetMapping("/passengers")
  List<Passenger> findAll() {
    return repository.findAll();
  }

  @GetMapping("/passengers/{id}")
  Passenger findById(@PathVariable Long id) {

    return repository.findById(id)
        .orElseThrow(() -> new NoPassengerException(id));
  }

  @PostMapping("/passengers")
  @ResponseStatus(HttpStatus.CREATED)
  Passenger createPassenger(@RequestBody Passenger passenger) {
    return repository.save(passenger);
  }
  
  @DeleteMapping("/passenger/{id}")
  void deletePassenger(@PathVariable Long id) {
    repository.deleteById(id);
  }

  @PatchMapping("/passengers/{id}")
  Passenger updatePassenger(@RequestBody Map<String, String> updates,
      @PathVariable Long id) {
    return repository.findById(id).map(passenger -> {
      
      String name = updates.get("name");
      if (name != null) {
        passenger.setName(name);
      }

      String registered = updates.get("registered");
      if (registered != null) {
        passenger
            .setRegistered(registered.equalsIgnoreCase("true") ? true : false);
      }

      Country newCountry = countryMap.get(updates.get("country"));
      if (newCountry != null) {
        passenger.setCountry(newCountry);
      }

      return repository.save(passenger);

    }).orElseThrow(() -> new NoPassengerException(id));
  }
}
