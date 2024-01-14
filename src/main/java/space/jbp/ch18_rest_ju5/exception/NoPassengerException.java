package space.jbp.ch18_rest_ju5.exception;

@SuppressWarnings("serial")
public class NoPassengerException extends RuntimeException {
  public NoPassengerException(Long id) {
    super("존재하지 않는 승객 ID: " + id);
  }
}
