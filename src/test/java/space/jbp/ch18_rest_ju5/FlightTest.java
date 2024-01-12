package space.jbp.ch18_rest_ju5;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import space.jbp.ch18_rest_ju5.beans.FlightBuilder;
import space.jbp.ch18_rest_ju5.model.Flight;
import space.jbp.ch18_rest_ju5.register.PassengerRegisterEvent;
import space.jbp.ch18_rest_ju5.register.RegisterManager;

@SpringBootTest
@Import(FlightBuilder.class)
class FlightTest {
  @Autowired
  private Flight flight;

  @Autowired
  private RegisterManager manager;

  @Test
  void testPassengersRegister() {
    flight.getPassengers().forEach(psgr -> {
      assertFalse(psgr.isRegistered());
      manager.getContext().publishEvent(new PassengerRegisterEvent(psgr));
      assertTrue(psgr.isRegistered());
    });
  }
}
