package space.jbp.ch18_rest_ju5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import space.jbp.ch18_rest_ju5.model.Country;
import space.jbp.ch18_rest_ju5.model.CountryRepository;

@RestController
public class CountryController {
  @Autowired
  private CountryRepository repository;
  
  @GetMapping("/countries")
  List<Country> findAll() {
    return repository.findAll();
  }
}
