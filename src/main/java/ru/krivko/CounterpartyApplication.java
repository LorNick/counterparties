package ru.krivko;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *  Курсовая работа: Контрагенты
 */
@SpringBootApplication
@EnableEncryptableProperties
public class CounterpartyApplication {

    public static void main(String[] args) {
        SpringApplication.run(CounterpartyApplication.class, args);
    }
}