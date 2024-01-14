package space.jbp.ch18_rest_ju5.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@NoArgsConstructor
public class Passenger {
  @Id
  @GeneratedValue
  private Long id;
  private String name;
  @ManyToOne
  private Country country;
  
  public Passenger(String name, Country country) {
    super();
    this.name = name;
    this.country = country;
  }

  private boolean registered;
}
