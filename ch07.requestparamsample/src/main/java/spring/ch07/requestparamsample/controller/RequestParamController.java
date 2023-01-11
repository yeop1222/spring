package spring.ch07.requestparamsample.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spring.ch07.requestparamsample.form.Form;

import java.time.LocalDate;

@Controller
public class RequestParamController {

    @GetMapping("show")
    public String showView() {
        //반환값으로 뷰 이름
        return "entry";
    }
/*
    @PostMapping("confirm")
    public String confirmView(
            Model model, @RequestParam String name, @RequestParam Integer age,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam LocalDate birth
            ) {
        //Model에 저장
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        model.addAttribute("birth", birth);

        //뷰 이름 리턴
        return "confirm";
    }
*/
    @PostMapping("confirm")
    public String confirmView(Form f) {
        return "confirm2";
    }
}
