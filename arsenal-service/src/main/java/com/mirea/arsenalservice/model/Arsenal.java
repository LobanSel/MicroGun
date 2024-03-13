package com.mirea.arsenalservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "arsenal")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Arsenal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String GunCode;
    private Integer quantity;
}
