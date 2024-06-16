package com.example.practice_project.controller;

import com.example.practice_project.domain.dto.AnimalDTO;
import com.example.practice_project.domain.dto.AnimalInsertDTO;
import com.example.practice_project.domain.vo.AnimalVO;
import com.example.practice_project.service.AnimalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/animal")
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalService animalService;

    // 첫 페이지는 무조건 index.html이어야만 하는가!
    @GetMapping
    public String index() {
        return "/animal/index";
    }

    // 동물 전체 목록을 보여주는 컨트롤러
    @GetMapping("/list")
    public String AnimalList(@RequestParam(value="pageNo", defaultValue = "1") int pageNo,
                             @RequestParam(value = "pageSize", defaultValue = "3") int pageSize,
                             Model model) {

        // 동물 테이블 안에 있는 데이터 행의 갯수를 가져오기
        int totalAnimals = animalService.countAnimals();

        // 올림을 사용하여 페이지수 구하기!
        int totalPages = (int)Math.ceil((double)totalAnimals/pageSize);

        List<AnimalDTO> animals = animalService.findAll(pageNo, pageSize);

        int pageGroupSize = 4;
        int startPage = ((pageNo - 1) / pageGroupSize) * pageGroupSize + 1;
        int endPage = Math.min(startPage + pageGroupSize - 1, totalPages);

        // html로 넘겨야하는 값들은?
        // 1. 데이터 2. 현재 페이지와 페이지 사이즈 3. 총 페이지 수
        model.addAttribute("animals", animals);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalPages", totalPages);

        // 4. 시작 페이지 수 5. 마지막 페이지 수
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "/animal/animallist";
    }

    // 동물 이름을 클릭하여 해당 동물의 세부정보를 보여주는 컨트롤러
    @GetMapping("/detail/{id}")
    public String AnimalDetail(@PathVariable int id, Model model) {
        model.addAttribute("animal", animalService.findById(id));
        return "/animal/animaldetail";
    }

    // 동물을 삭제하는 컨트롤러
    @GetMapping("/detail/delete/{id}")
    public String AnimalDelete(@PathVariable int id) {
        animalService.deleteById(id);
        return "redirect:/animal/list";
    }

    // insert 페이지로 이동하는 컨트롤러
    @GetMapping("/writeanimal")
    public String AnimalInsertForm(Model model) {
        model.addAttribute("animal", new AnimalInsertDTO());
        return "/animal/animalinsert";
    }

    // 동물 정보 삽입하여 리스트 페이지로 이동하는 컨트롤러
    @PostMapping("writeanimal")
    public String AnimalInsert(@ModelAttribute AnimalInsertDTO animalInsertDTO) {
        AnimalVO vo = AnimalVO.toEntity(animalInsertDTO);

        // id가 넘어왔다면? update문 실행!
        // 0보단 null로 하는 것이 좋다.
        if(vo.getId() != 0){
            animalService.edit(vo);
            return "redirect:/animal/detail/" +vo.getId();
        }

        animalService.save(vo);
        return "redirect:/animal/list";
    }

    // 수정하는폼을 불러오는 컨트롤러
    @GetMapping("/edit/{id}")
    public String AnimalEditForm(@PathVariable int id, Model model) {
        model.addAttribute("animal", animalService.findById(id));
        return "/animal/animalinsert";
    }
}
