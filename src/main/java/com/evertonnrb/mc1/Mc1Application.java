package com.evertonnrb.mc1;

import com.evertonnrb.mc1.domain.*;
import com.evertonnrb.mc1.domain.enuns.EstadoPagamento;
import com.evertonnrb.mc1.domain.enuns.TipoCliente;
import com.evertonnrb.mc1.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@SpringBootApplication
public class Mc1Application implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(Mc1Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
