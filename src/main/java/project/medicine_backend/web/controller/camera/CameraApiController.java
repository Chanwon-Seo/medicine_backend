package project.medicine_backend.web.controller.camera;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.medicine_backend.config.auth.PrincipalDetails;
import project.medicine_backend.domain.service.CameraService;
import project.medicine_backend.domain.service.MedicineService;
import project.medicine_backend.web.controller.camera.form.MedicineSaveForm;

import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/camera")
@RequiredArgsConstructor
public class CameraApiController {
    private final CameraService cameraService;

    private final MedicineService medicineService;

    ImageSaveMemory imageSaveMemory = new ImageSaveMemory();

    @GetMapping("/add")
    public String videoGET() {
        log.info("이미지 스트리밍 중입니다.");
        return "camera/cameraStreamMain";
    }

    @PostMapping("/img/upload")
    public String handleFileUploadV2(@RequestPart("check_img") MultipartFile files, RedirectAttributes redirectAttributes) throws IOException {

        long imgId = cameraService.join(files);

        String findImg = cameraService.findImage(imgId);
        imageSaveMemory.image_key = imgId;
        imageSaveMemory.imagePath = findImg;

        redirectAttributes.addAttribute("imgId", imgId);

        return "redirect:/camera/img/check";
    }

    @GetMapping("/img/check")
    public String checkImg(Model model) {

        model.addAttribute("imgPath", imageSaveMemory.imagePath);

        return "camera/ROI";
    }

    @GetMapping("/img/add")
    public String imgAdd(@ModelAttribute MedicineSaveForm medicineSaveForm, Model model) {

        model.addAttribute("medicineSaveForm", medicineSaveForm);
        model.addAttribute("imgPath", imageSaveMemory.imagePath);

        return "camera/medicineForm";
    }

    @PostMapping("/img/add")
    public String imgSave(@ModelAttribute MedicineSaveForm medicineSaveForm, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        medicineService.join(medicineSaveForm, principalDetails.getUsername());

        return "redirect:/main";
    }

}

class ImageSaveMemory {
    public long image_key;
    public String imagePath;

    public ImageSaveMemory() {
    }
}