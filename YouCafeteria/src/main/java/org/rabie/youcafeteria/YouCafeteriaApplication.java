package org.rabie.youcafeteria;

import org.rabie.youcafeteria.domain.AppUser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "org.rabie.youcafeteria")
@EnableJpaRepositories(basePackages = "org.rabie.youcafeteria.repository")
public class YouCafeteriaApplication {

    public static void main(String[] args) {
        SpringApplication.run(YouCafeteriaApplication.class, args);

    }

}
