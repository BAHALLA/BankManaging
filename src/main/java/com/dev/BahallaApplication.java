package com.dev;

import com.dev.dao.ClientRepository;
import com.dev.dao.CompteRepository;
import com.dev.dao.OperationRepository;
import com.dev.entities.*;
import com.dev.metier.IBanqueMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Date;

@SpringBootApplication
public class BahallaApplication implements CommandLineRunner {

    public static void main(String[] args)
    {
         SpringApplication.run(BahallaApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {

    }
}
