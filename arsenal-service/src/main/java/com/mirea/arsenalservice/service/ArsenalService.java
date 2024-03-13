package com.mirea.arsenalservice.service;

import com.mirea.arsenalservice.dto.ArsenalResponse;
import com.mirea.arsenalservice.repo.ArsenalRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArsenalService {

    private final ArsenalRepository arsenalRepository;

    @Transactional(readOnly = true)
    @SneakyThrows
    public List<ArsenalResponse> isAlreadyGet(List<String> gunCode) {
        return arsenalRepository.findBygunCodeIn(gunCode).stream()
                .map(arsenal -> ArsenalResponse
                        .builder()
                        .gunCode(arsenal.getGunCode())
                        .isAlreadyGet(arsenal.getQuantity() > 0)
                        .build()
                ).toList();
    }
}
