package ru.otus.hw16.frontend;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.otus.hw16.frontend.server.FrontendServer;

//https://www.baeldung.com/spring-boot-console-app
@SpringBootApplication
@AllArgsConstructor
public class HW16FrontendApplication implements CommandLineRunner {
    private FrontendServer frontendServer;

    public static void main(String[] args) {
        SpringApplication.run(HW16FrontendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        frontendServer.startServer();
    }
}
