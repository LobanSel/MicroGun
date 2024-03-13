package com.mirea.gunservice.controller;

import com.mirea.gunservice.dto.GunRequest;
import com.mirea.gunservice.dto.GunResponse;
import com.mirea.gunservice.service.GunService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/gun")
@RequiredArgsConstructor
public class GunController {

    private final GunService gunService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createGun(@RequestBody GunRequest gunRequest){
        gunService.createGun(gunRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GunResponse> getAllGuns(){
        return gunService.getAllGuns();
    }
}
