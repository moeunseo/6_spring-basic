package com.example.e_firstpro.mapper;

import com.example.e_firstpro.domain.vo.ProductVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class ProductMapperTest {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductVO productVO;

    @Test
    void selectByIdTest(){
        int pk = 3;
        log.info(productMapper.selectById(pk).toString());
    }

    @Test
    void selectAllTest(){
        productMapper.selectAll().stream().map(ProductVO::toString).forEach(log::info);
        productMapper.selectAll().forEach(birdVO -> {log.info(birdVO.toString());});
    }

    //안됨
    @Test
    void insertTest(){
        ProductVO vo =
                ProductVO.builder()
                        .name("전자사전")
                        .price(500.0)
                        .category("전자기기")
                        .description("상태보통")
                        .build();
        productMapper.insert(vo);
    }

    // 안됨
    @Test
    void updateByIdTest(){
        int pk = 18;
        Double price = 455.5;

        // 가격을 변경하는 update문
        ProductVO productvo =
                ProductVO.builder()
                        .id(pk)
                        .price(price)
                        .build();
        ProductVO updateVO = productMapper.selectById(pk);
        updateVO.setPrice(price);
        productMapper.updateById(updateVO);
    }

    @Test
    void deleteByIdTest(){
        int pk = 16;
        productMapper.deleteById(pk);
    }
}