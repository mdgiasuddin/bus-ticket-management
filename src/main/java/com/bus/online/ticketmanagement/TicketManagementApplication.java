package com.bus.online.ticketmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TicketManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicketManagementApplication.class, args);
    }

}
