package com.xml.publications;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PublicationsApplication {

    public static void main(String[] args) {

        SpringApplication.run(PublicationsApplication.class, args);

        System.out.println(System.getenv("GMAIL_USERNAME"));
    }

}
