package yegam.cultureservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CultureServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(CultureServiceApplication.class, args);
  }

}
