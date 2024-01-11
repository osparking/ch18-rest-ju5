package space.jbp.ch18_rest_ju5.register;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import lombok.Getter;

@Service
@Getter
public class RegisterManager implements ApplicationContextAware {
  private ApplicationContext context;

  @Override
  public void setApplicationContext(ApplicationContext context)
      throws BeansException {
    this.context = context;
  }
}
