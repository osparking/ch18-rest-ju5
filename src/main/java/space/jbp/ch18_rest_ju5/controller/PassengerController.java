package space.jbp.ch18_rest_ju5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import space.jbp.ch18_rest_ju5.exception.NoPassengerException;
import space.jbp.ch18_rest_ju5.model.Passenger;
import space.jbp.ch18_rest_ju5.model.PassengerRepository;

@RestController
public class PassengerController {
  @Autowired
  private PassengerRepository repository;

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
}
