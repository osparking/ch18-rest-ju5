package space.jbp.ch18_rest_ju5.model;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@NoArgsConstructor
@EqualsAndHashCode
public class Passenger {
  @Id
  @GeneratedValue
  private Long id;
  private String name;
  @JdbcTypeCode(SqlTypes.JSON)
  private Country country;
  
  public Passenger(String name, Country country) {
    super();
    this.name = name;
    this.country = country;
  }

  private boolean registered;
}
