package project.medicine_backend.web.controller.medicine;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/medicine")
@RequiredArgsConstructor
public class MedicineController {

    /**
     * 사용자가 웹 페이지에 접근한다.
     */
    @GetMapping("/stream")
    public String medicine_stream() {
        return "medicine/medicineStreamMain";
    }

}
