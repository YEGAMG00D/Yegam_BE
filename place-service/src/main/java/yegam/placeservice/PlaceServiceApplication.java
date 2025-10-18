package yegam.placeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PlaceServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(PlaceServiceApplication.class, args);
  }

}
