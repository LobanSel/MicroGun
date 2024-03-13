package com.mirea.gunservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(value = "gun")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Gun {
    @Id
    private String id;
    private String model;
    private String serialNumber;
    private String description;
    private BigDecimal price;
}
