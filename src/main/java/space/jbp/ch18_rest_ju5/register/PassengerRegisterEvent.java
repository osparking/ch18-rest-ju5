package space.jbp.ch18_rest_ju5.register;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;
import space.jbp.ch18_rest_ju5.model.Passenger;

@SuppressWarnings("serial")
@Getter
public class PassengerRegisterEvent extends ApplicationEvent {
  private Passenger passenger;

  public PassengerRegisterEvent(Passenger passenger) {
    super(passenger);
    this.passenger = passenger;
  }
}
