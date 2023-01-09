package spring.ch06.springmvcmodelsample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("hello")
public class HelloModelController {

    @GetMapping("model")
    public String helloView(Model model) {
        //model에 데이터 저장
        model.addAttribute("msg", "타임리프!!!");

        //반환값으로 뷰 이름 리턴
        return "helloThymeleaf";
    }
}
