package org.anton.vadarodtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class VadarodTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(VadarodTestApplication.class, args);
    }

}
