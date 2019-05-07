package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String [] args) {
//        Flyway flyway = Flyway.configure().dataSource("jdbc:h2:file:./target/foobar", "sa", null).load();

//        flyway.migrate();
        SpringApplication.run(Application.class, args);
    }
}
