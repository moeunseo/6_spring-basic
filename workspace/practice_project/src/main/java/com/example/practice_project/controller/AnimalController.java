package com.example.practice_project.controller;

import com.example.practice_project.domain.dto.AnimalInsertDTO;
import com.example.practice_project.domain.dto.LoginDTO;
import com.example.practice_project.domain.dto.UserDTO;
import com.example.practice_project.domain.vo.AnimalVO;
import com.example.practice_project.domain.vo.UserVO;
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

    // 회원가입 페이지로 이동하는 컨트롤러
    @GetMapping("/joinpage")
    public String join(Model model) {
        model.addAttribute("user", new UserDTO());
        return "/animal/joinpage";
    }

    // 회원가입 컨트롤러
    @PostMapping("/joinpage")
    public String joinInsert(@ModelAttribute UserDTO dto) {
        UserVO vo = UserVO.toEntity(dto);
        animalService.userSave(vo);
        return "redirect:/animal";
    }

    // 로그인 페이지로 이동하는 컨트롤러
    @GetMapping("/loginpage")
    public String login(Model model) {
        model.addAttribute("user", new LoginDTO());
        return "/animal/loginpage";
    }

    // 로그인을 하기위한 컨트롤러
    //  게시판 진행해보고 해보기!
    @PostMapping("/loginpage")
    public String loginCheck(@ModelAttribute LoginDTO dto) {
        // 전체 user 정보를 담는 리스트
        List<LoginDTO> user = animalService.findUser(dto.getId());
        for (LoginDTO loginDTO : user) {
            if(loginDTO.getId().equals(dto.getId())) {
                if(loginDTO.getPwd() == dto.getPwd()) {
                    return "redirect:/animal/list";
                }
            }
        }
        return "redirect:/animal";
    }

    // 동물 전체 목록을 보여주는 컨트롤러
    @GetMapping("/list")
    public String AnimalList(Model model) {
        model.addAttribute("animals", animalService.findAll());
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
