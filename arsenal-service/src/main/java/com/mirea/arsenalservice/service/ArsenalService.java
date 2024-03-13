package com.mirea.arsenalservice.service;

import com.mirea.arsenalservice.repo.ArsenalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ArsenalService {

    private final ArsenalRepository arsenalRepository;

    @Transactional(readOnly = true)
    public boolean isAlreadyGet(String GunCode){
        return arsenalRepository.findByGunCode(GunCode).isPresent();
    }
}
