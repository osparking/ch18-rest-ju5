package space.jbp.ch18_rest_ju5;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(FlightBuilder.class)
@SpringBootTest
class FlightBuilderTest {
  @Autowired
  FlightBuilder builder;

  @Test
  void test() throws FileNotFoundException, IOException {
    assertNotNull(builder.buildFlight());
  }
}
