package ru.otus.hw16.messageServer;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.otus.hw16.messageServer.server.MessageSystemSocketServer;

////https://www.baeldung.com/spring-boot-console-app
@SpringBootApplication
@AllArgsConstructor
public class HW16MessageServerApplication implements CommandLineRunner {
    private MessageSystemSocketServer messageSystemSocketServer;

    public static void main(String[] args) {
        SpringApplication.run(HW16MessageServerApplication.class, args);
    }

    @Override
    public void run(String... args) {
        messageSystemSocketServer.startServer();
    }
}
