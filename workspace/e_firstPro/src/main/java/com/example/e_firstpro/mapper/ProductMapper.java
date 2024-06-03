package com.example.e_firstpro.mapper;

import com.example.e_firstpro.domain.vo.ProductVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    ProductVO selectById(Long id);

    List<ProductVO> selectAll();

    void insert(ProductVO productVO);

    void updateById(ProductVO productVO);

    void deleteById(Long id);
}
