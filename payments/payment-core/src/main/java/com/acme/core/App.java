package com.acme.core;

import com.acme.core.commons.exceptions.ParseException;
import com.acme.core.commons.utils.InputParse;
import com.acme.core.dtos.InputDTO;
import com.acme.core.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static java.lang.System.exit;

@SpringBootApplication
public class App implements CommandLineRunner {

    @Autowired
    private PaymentService paymentService;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        if (args.length > 0) {
            throw new ParseException("args can not be empty");
        }

        InputDTO inputDTO = InputDTO.parse(args[0]);
        String message = String.format("The amount to pay %1$s is: %2$d USD", inputDTO.getName(), this.paymentService.calculate(inputDTO.getWorkDays()));
        System.out.println(message);

        exit(0);
    }
}
