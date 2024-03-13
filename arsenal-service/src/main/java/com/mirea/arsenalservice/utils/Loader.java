package com.mirea.arsenalservice.utils;

import com.mirea.arsenalservice.model.Arsenal;
import com.mirea.arsenalservice.repo.ArsenalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Loader implements CommandLineRunner {
    private final ArsenalRepository arsenalRepository;

    @Override
    public void run(String... args) throws Exception {
        Arsenal arsenal = new Arsenal();
        arsenal.setGunCode("AК-47");
        arsenal.setQuantity(0);

        Arsenal arsenal1 = new Arsenal();
        arsenal1.setGunCode("AКСУ");
        arsenal1.setQuantity(45);

        arsenalRepository.save(arsenal);
        arsenalRepository.save(arsenal1);
    }
}
