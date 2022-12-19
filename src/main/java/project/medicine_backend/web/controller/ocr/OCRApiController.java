package project.medicine_backend.web.controller.ocr;

import project.medicine_backend.web.controller.ocr.form.OcrSaveForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j // 테스트를 위한 로그 어노테이션
@Controller
@RequestMapping("/ocr")
@RequiredArgsConstructor
public class OCRApiController {

    @GetMapping
    public String ocrMain() {
        log.info("OCR로 접근하였습니다."); //페이지 접근 로그
        return "ocr/ocrMain";
    }

    @GetMapping("/add")
    public String ocrText(@ModelAttribute(name = "ocrSaveForm") OcrSaveForm form) {
        log.info("OCR로 받은 문자열 값은 = {}", form.getText()); //Api를 통해 넘어온 파라미터값을 로그로 출력한다.
        return "ocr/ocrText";
    }

}
