package team.a501.rif;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class RifApplication {
    public static void main(String[] args) {
        SpringApplication.run(RifApplication.class, args);
    }
}
