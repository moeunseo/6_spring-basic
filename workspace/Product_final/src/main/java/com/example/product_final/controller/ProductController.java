package com.example.product_final.controller;

import com.example.product_final.domain.dto.ProductDTO;
import com.example.product_final.domain.dto.ProductDTO2;
import com.example.product_final.domain.vo.ProductVO;
import com.example.product_final.service.PagingService;
import com.example.product_final.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j
// RequestMapping은 FrontController 하는 역할을 한다.
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final PagingService pagingService;

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
    public String writeOk(@ModelAttribute ProductDTO2 product, RedirectAttributes redirectAttributes){
        ProductVO vo = ProductVO.toEntity(product);

        // 업데이트가 넘어왔다면
        if(vo.getId() != null){
            productService.edit(vo);
            return "redirect:/product/detail/" + vo.getId();
        }

        // 만약, 게시글 추가가 이루어진다면, 리스트를 보여주고 html을 하나 띄어줄건데
        // 그 html의 내용은 '게시글이 정상적으로 추가되었습니다.'
        int isInserted = productService.save(vo);

        // 빨간박스 띄우는 거 연습!
//        int isInserted = 0;

        if(isInserted != 1){
            redirectAttributes.addFlashAttribute("error", "예상치 못한 에러가 발생했습니다.");
        }
        else{
            redirectAttributes.addFlashAttribute("msg", "게시글 정상 추가되었습니다.");
        }
        // html 이 아닌, 컨트롤러를 요청한다.
        return "redirect:/product/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "/product/writeForm";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        int isDeleted = productService.delete(id);
        if(isDeleted != 1){
            redirectAttributes.addFlashAttribute("error", "예상치 못한 에러가 발생했습니다.");
        }
        else{
            redirectAttributes.addFlashAttribute("msg", "게시글 정상 삭제되었습니다.");
        }
        return "redirect:/product/list";
    }
// =====================================================================================================================
    // 페이징 처리 연습
    @GetMapping("/paging")
    public String paging(@RequestParam(value="pageNo", defaultValue = "1") int pageNo,
                         @RequestParam(value = "pageSize", defaultValue = "5") int pageSize,
                         Model model) {

        int totalProducts = pagingService.countProducts();
        // 올림을 사용하여 페이지수 구하기!
        int totalPages = (int)Math.ceil((double)totalProducts/pageSize);

        List<ProductDTO> products = pagingService.selectPaging(pageNo, pageSize);

        int pageGroupSize = 3;
        int startPage = ((pageNo - 1) / pageGroupSize) * pageGroupSize + 1;
        int endPage = Math.min(startPage + pageGroupSize - 1, totalPages);

        // html로 넘겨야하는 값들은?
        // 1. 데이터 2. 현재 페이지와 페이지 사이즈 3. 총 페이지 수
        model.addAttribute("products", products);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalPages", totalPages);

        // 4. 시작 페이지 수 5. 마지막 페이지 수
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "/product/paging";
    }
}