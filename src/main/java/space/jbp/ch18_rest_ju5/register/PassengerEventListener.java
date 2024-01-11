package space.jbp.ch18_rest_ju5.register;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import space.jbp.ch18_rest_ju5.model.Passenger;

@Service
public class PassengerEventListener {
  @EventListener
  public void actOnPassengerRegister(PassengerRegisterEvent event) {
    Passenger passenger = event.getPassenger();
    System.out.println("등록전: " + passenger);
    passenger.setRegistered(true);
  }
}
