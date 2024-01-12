package space.jbp.ch18_rest_ju5.model;

import java.util.Collection;
import java.util.HashSet;

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
  private Collection<Passenger> passengers = new HashSet<>();;

  public Flight(String flightNo, int seats) {
    super();
    this.flightNo = flightNo;
    this.seats = seats;
  }

  public boolean addPassenger(Passenger passenger) {
    if (passengers.size() > seats) {
      throw new RuntimeException("좌석 부족으로 탑승이 거부되었음.");
    }
    return passengers.add(passenger);
  }
  
  public boolean removePassenger(Passenger passenger) {
    return passengers.remove(passenger);
  }
}
