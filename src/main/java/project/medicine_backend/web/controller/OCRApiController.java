package project.medicine_backend.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/OCR")
@RequiredArgsConstructor
public class OCRApiController {

    @GetMapping("/add")
    public void  ocrGET(@RequestParam String text, HttpServletResponse response) throws IOException {
        log.info("OCR로 들어온 값은 = {}", text);
        response.getWriter().write("OK server Check");
    }
}
