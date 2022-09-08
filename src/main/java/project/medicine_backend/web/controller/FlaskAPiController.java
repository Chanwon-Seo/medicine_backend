package project.medicine_backend.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/webCamera")
public class FlaskAPiController {

    @GetMapping
    public void test(HttpServletResponse response) throws IOException {
        log.info("camera로 접근하였습니다.");
        response.getWriter().write("ok");
    }

}
