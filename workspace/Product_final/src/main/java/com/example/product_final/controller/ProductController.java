package com.example.product_final.controller;

import com.example.product_final.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
// RequestMapping은 FrontController 하는 역할을 한다.
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public String index() {
        // 기본 시작 경로는 templates
        // 우리 index페이지는 product안에 있기에 경로를 명시!
        // 뒤에 .html 확장자는 생략이 가능하다.
        return "/product/index";
    }

    @GetMapping("/list")
    // Model은 컨트롤러에서 html로 데이터를 전달하기 위해 사용하는 객체
    // 해당 객체의 데이터를 저장, html로 가서 thyeleaf 뿌려줌
    public String list(Model model) {
        // 앞에 적어준 문자열은 html내에서 사용할 데이터의 이름!
        // 뒤에는 실제 전송하는 데이터

        // th:if 사용 전 데이터 하나만 넘기기
//        model.addAttribute("product", productService.findAll().get(0));
        model.addAttribute("products", productService.findAll());
        return "/product/productlist";
    }
}