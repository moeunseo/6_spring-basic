package koreait.spring.springtest1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// 화면에 이동없이 데이터만 받아서 화면을 보여준다.
@RestController
public class TestController {
    @GetMapping("/test") //test라는 경로로 요청이 들어온다면 test메소드 실행!
    public String test(){
        return "박지민 바보";
    }
}
