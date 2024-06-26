package com.example.product_final.service;

import com.example.product_final.domain.dto.ProductDTO;
import com.example.product_final.domain.dto.ProductDetailDTO;
import com.example.product_final.domain.vo.ProductVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    // 전체 물품 리스트를 반환하는 select
    List<ProductDTO> findAll();

    // 상세보기 페이지로 보낼 데이터 select
    ProductDetailDTO findById(Long id);

    // 새 물품 등록 insert
    int save(ProductVO vo);

    // 물품 정보수정 update
    void edit(ProductVO vo);

    // 물품 삭제 delete
    int delete(Long id);
}

