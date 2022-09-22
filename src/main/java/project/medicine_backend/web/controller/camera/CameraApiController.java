package project.medicine_backend.web.controller.camera;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/camera/api")
public class CameraApiController {

    @GetMapping
    public String rasPiCameraGET() {
        log.info("GET - raspiStream.html open");
        return "camera/raspiStream";
    }

}