package com.example.product_final.mapper;

import com.example.product_final.domain.dto.ProductDTO;
import com.example.product_final.domain.dto.ProductDetailDTO;
import com.example.product_final.domain.vo.ProductVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<ProductVO> selectTest();

    // 리스트 화면에 뿌려줄 sql
    List<ProductDTO> selectList();

    // 상세 페이지를 보여줄 sql
    ProductDetailDTO selectOne(Long id);

    // 새 물품 등록 sql
    void insert(ProductVO vo);

    // update sql
    void update(ProductVO vo);
}
