package com.example.product_final.controller;

import com.example.product_final.domain.dto.ProductDTO2;
import com.example.product_final.domain.vo.ProductVO;
import com.example.product_final.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    // 해당 객체의 데이터를 저장, html로 가서 thymeleaf 뿌려줌
    public String list(Model model) {
        // 앞에 적어준 문자열은 html내에서 사용할 데이터의 이름!
        // 뒤에는 실제 전송하는 데이터

        // th:if 사용 전 데이터 하나만 넘기기
//        model.addAttribute("product", productService.findAll().get(0));
        // 한 페이지에 여러 개의 데이터를 가지고 올 수 있다.
        model.addAttribute("products", productService.findAll());
        return "/product/productlist";
    }

    // @PathVariable
    // 경로로 넘어온 값을 매개변수와 매핑시킬 때 사용한다.
    // PathVarible의 이름과 매개변수의 이름이 동일하다면 자동으로 매핑되기에 생략 가능!
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "/product/detail";
    }

    // writeForm으로 이동하는 컨트롤러!
    @GetMapping("/write")
    public String goWriteForm(Model model) {
        // html에서 데이터를 다시 받아올 때도 model객체에 담아서 받아와야하는데,
        // writeForm에서 바로 model객체를 사용할 수 없기에
        // writeForm을 요청할 때부터 넘겨준다.
        model.addAttribute("product", new ProductDTO2());
        return "/product/writeForm";
    }

    //
    @PostMapping("/write")
    public String writeOk(@ModelAttribute ProductDTO2 product){
        ProductVO vo = ProductVO.toEntity(product);

        // update가 넘어왔다면
        if(vo.getId() != null){
            productService.edit(vo);
            return "redirect:/product/detail/" + vo.getId();
        }
        productService.save(vo);

        // html이 아닌 controller를 요청하는 문법
        return "redirect:/product/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "/product/writeForm";
    }
}