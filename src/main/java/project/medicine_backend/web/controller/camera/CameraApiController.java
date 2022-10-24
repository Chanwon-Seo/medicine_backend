package project.medicine_backend.web.controller.camera;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import project.medicine_backend.domain.Camera;
import project.medicine_backend.service.CameraService;
import project.medicine_backend.web.controller.camera.form.CameraSaveForm;

import java.time.LocalDateTime;

@Slf4j
@Controller
@RequestMapping("/camera/api")
@RequiredArgsConstructor
public class CameraApiController {

    private final CameraService cameraService;

    @GetMapping
    public String cameraMainGET() {
        log.info("GET - stream_Data.html open");
        return "camera/stream_Data";
    }

    @ResponseBody
    @GetMapping("/add")
    public Object rasPiCameraGET(
            @RequestParam Double x, @RequestParam Double y,
            @RequestParam Double w, @RequestParam Double h) {
        CameraSaveForm form = new CameraSaveForm(x, y, w, h, LocalDateTime.now());

        Camera join = cameraService.join(form);

        return join;
    }

}