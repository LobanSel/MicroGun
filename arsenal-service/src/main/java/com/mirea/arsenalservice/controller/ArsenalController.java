package com.mirea.arsenalservice.controller;


import com.mirea.arsenalservice.service.ArsenalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/arsenal")
@Slf4j
public class ArsenalController {

    private final ArsenalService arsenalService;

    @GetMapping("/{gun-code}")
    @ResponseStatus(HttpStatus.OK)
    public boolean isAlreadyGet(@PathVariable("gun-code") String GunCode) {
        return arsenalService.isAlreadyGet(GunCode);
    }
}
