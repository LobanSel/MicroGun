package com.mirea.arsenalservice.controller;


import com.mirea.arsenalservice.dto.ArsenalResponse;
import com.mirea.arsenalservice.service.ArsenalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/arsenal")
@Slf4j
public class ArsenalController {

    private final ArsenalService arsenalService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ArsenalResponse> isAlreadyGet(@RequestParam List<String> gunCode) {
        log.info("Received arsenal check request for skuCode: {}", gunCode);
        return arsenalService.isAlreadyGet(gunCode);
    }
}
