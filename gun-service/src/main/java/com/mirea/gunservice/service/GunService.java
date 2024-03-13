package com.mirea.gunservice.service;

import com.mirea.gunservice.dto.GunRequest;
import com.mirea.gunservice.dto.GunResponse;
import com.mirea.gunservice.model.Gun;
import com.mirea.gunservice.repo.GunRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GunService {

    private final GunRepository gunRepository;

    public void createGun(GunRequest gunRequest){
        Gun gun = Gun.builder()
                .model(gunRequest.getModel())
                .serialNumber(gunRequest.getSerialNumber())
                .description(gunRequest.getDescription())
                .price(gunRequest.getPrice())
                .build();

        gunRepository.save(gun);
        log.info("Gub is added to base");
    }

    public List<GunResponse> getAllGuns() {
        List<Gun> guns = gunRepository.findAll();

        return  guns.stream().map(this::mapToGunsResponse).toList();
    }

    private GunResponse mapToGunsResponse(Gun gun) {
        return GunResponse.builder()
                .id(gun.getId())
                .model(gun.getModel())
                .serialNumber(gun.getSerialNumber())
                .description(gun.getDescription())
                .price(gun.getPrice())
                .build();
    }
}
