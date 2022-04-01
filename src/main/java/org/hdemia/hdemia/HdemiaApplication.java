package org.hdemia.hdemia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "org.hdemia.hdemia.model.repository")
public class HdemiaApplication {

    public static void main(String[] args) {
        SpringApplication.run(HdemiaApplication.class, args);
    }

}
