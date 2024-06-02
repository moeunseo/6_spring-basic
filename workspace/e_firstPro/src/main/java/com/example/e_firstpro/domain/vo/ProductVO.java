package com.example.e_firstpro.domain.vo;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductVO {
    private int id;
    private String name;
    private Double price;
    private String category;
    private String description;

    @Builder
    public ProductVO(int id, String name, Double price, String category, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.description = description;
    }
}
