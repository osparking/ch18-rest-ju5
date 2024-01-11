package space.jbp.ch18_rest_ju5.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Passenger {
  private String name;
  private Country country;
  
  public Passenger(String name, Country country) {
    super();
    this.name = name;
    this.country = country;
  }

  private boolean registered;
}
