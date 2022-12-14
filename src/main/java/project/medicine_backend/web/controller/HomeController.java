package project.medicine_backend.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        log.info("mainPage");
        return "main";
    }

    @GetMapping("/main")
    public String main() {
        log.info("mainPage");
        return "index";
    }

}
