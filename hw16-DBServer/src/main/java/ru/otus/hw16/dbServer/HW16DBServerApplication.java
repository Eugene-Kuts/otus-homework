package ru.otus.hw16.dbServer;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.otus.hw16.dbServer.server.DBSocketServer;

//https://www.baeldung.com/spring-boot-console-app
@SpringBootApplication
@AllArgsConstructor
public class HW16DBServerApplication implements CommandLineRunner {
    private DBSocketServer dbSocketServer;

    public static void main(String[] args) {
        SpringApplication.run(HW16DBServerApplication.class, args);
    }

    @Override
    public void run(String... args) {
        dbSocketServer.startServer();
    }
}
