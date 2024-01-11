package space.jbp.ch18_rest_ju5.model;

import java.util.Collection;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Flight {
  private String flightNo;
  private int seats;
  private Collection<Passenger> passengers;

  public Flight(String flightNo, int seats) {
    super();
    this.flightNo = flightNo;
    this.seats = seats;
  }
}
