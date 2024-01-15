package space.jbp.ch18_rest_ju5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
  
  @PostMapping("/passengers")
  @ResponseStatus(HttpStatus.CREATED)
  Passenger createPassenger(@RequestBody Passenger passenger) {
    return repository.save(passenger);
  }
}
