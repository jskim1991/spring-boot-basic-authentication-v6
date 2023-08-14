package io.jay.service;

import io.jay.service.repository.AuthorityEntity;
import io.jay.service.repository.AuthorityRepository;
import io.jay.service.repository.UserEntity;
import io.jay.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.List;

@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}

@Component
@RequiredArgsConstructor
class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    @Override
    public void run(String... args) throws Exception {
        var jay = new UserEntity("jay", "{noop}1234");
        userRepository.saveAll(List.of(jay));

        var authorities = List.of(
            new AuthorityEntity("READ", jay),
            new AuthorityEntity("WRITE", jay)
        );
        authorityRepository.saveAll(authorities);
    }
}
