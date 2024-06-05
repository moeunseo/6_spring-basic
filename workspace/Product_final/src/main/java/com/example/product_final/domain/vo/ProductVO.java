package com.example.product_final.domain.vo;

import com.example.product_final.domain.dto.ProductDTO2;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Getter
@ToString
public class ProductVO {
    private Long id;
    private String name;
    private Double price;
    private String category;
    private String description;

    @Builder
    public ProductVO(Long id, String name, Double price, String category, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.description = description;
    }

    // DTO를 VO로 바꿔주는 메소드
    // INSERT를 할 때 HTML에서 넘어온 데이터를 파싱해야하는데..
    // VO는 setter가 없기에 파싱이 불가능하여, dto로 파싱한 이후에 문제가 없다면 vo로 변환!
    // 그리고 insert 실행
    public static ProductVO toEntity(ProductDTO2 productDTO2) {
        return ProductVO.builder().id(productDTO2.getId())
                .name(productDTO2.getName())
                .price(productDTO2.getPrice())
                .category(productDTO2.getCategory())
                .description(productDTO2.getDescription()).build();
    }
}
