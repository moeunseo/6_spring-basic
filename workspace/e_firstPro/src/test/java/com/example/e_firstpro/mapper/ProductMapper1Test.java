package com.example.e_firstpro.mapper;

import com.example.e_firstpro.domain.vo.ProductVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
class ProductMapper1Test {

    @Autowired
    private ProductMapper1 productMapper1;

    // xml파일 안에서 name을 like로 받는다.
    // ea가 들어간 모든 name의 갯수를 결과로 받아온다.
    @Test
    void findByNameTest(){
        String search = "ea";
        List<ProductVO> productVOS = productMapper1.findByName(search);
        log.info("검색 결과 갯수: " + productVOS.size());
        productVOS.stream().map(ProductVO::toString).forEach(log::info);
    }

    // <where>을 사용하여 여러 조건문을 실행할 수 있다.
    // 결과는 category와 price 참인 조건에 대한 결과만 가지고 온다.
    @Test
    void findByConditionsTest(){
//        String name = null;
//        String category = "Electronics";
//        int price = 300;
        String name = null;
        String category = null;
        int price = 0;

        List<ProductVO> productVOS = productMapper1.findByConditions(name, category, price);
        log.info("검색 결과 갯수: " + productVOS.size());
        productVOS.stream().map(ProductVO::toString).forEach(log::info);
    }

    // name이 Apple iPhone인 행만 찾아와서 결과로 보여준다.
    @Test
    void findByChooseTest(){
        String name = "Apple iPhone";
        String category = "Electronics";
        int price = 1000;

        List<ProductVO> productVOS = productMapper1.findByChoose(name, category, price);
        log.info("검색 결과 갯수: " + productVOS.size());
        productVOS.stream().map(ProductVO::toString).forEach(log::info);
    }

    @Test
    void update(){
        ProductVO vo =
                ProductVO.builder()
                        .id(17L)
                        .name("모은서")
//                        .price(500.0)
//                        .category("전자기기")
                        .description("awesome")
                        .build();
        productMapper1.update(vo);
    }
}