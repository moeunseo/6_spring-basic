package com.example.product_final.service;

import com.example.product_final.domain.dto.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PagingService {

    // 비지니스 로직!
    // 화면에서 넘어온 페이지 번호와 페이지 사이즈를 이용해서 start와 end를 구해서 mapper로 넘길 예정!
    List<ProductDTO> selectPaging(int pageNo, int pageSize);

    int countProducts();
}
