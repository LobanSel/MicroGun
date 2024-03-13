package com.mirea.gunservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GunResponse {
    private String id;
    private String model;
    private String serialNumber;
    private String description;
    private BigDecimal price;
}
