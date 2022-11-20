package project.medicine_backend.web.controller.yolo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.medicine_backend.config.auth.PrincipalDetails;
import project.medicine_backend.domain.service.MedicineService;
import project.medicine_backend.domain.service.YoloService;
import project.medicine_backend.web.controller.yolo.form.MedicineSaveForm;

import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/yolo")
@RequiredArgsConstructor
public class YoloApiController {
    private final YoloService yoloService;

    private final MedicineService medicineService;

    ImageSaveMemory imageSaveMemory = new ImageSaveMemory();

    @GetMapping("/add")
    public String videoGET() {
        log.info("이미지 스트리밍 중입니다.");
        return "yolo/yoloStreamMain";
    }

    @PostMapping("/img/upload")
    public String handleFileUploadV2(@RequestPart("check_img") MultipartFile files, RedirectAttributes redirectAttributes) throws IOException {
        long imgId = yoloService.join(files);

        String findImg = yoloService.findImage(imgId);
        imageSaveMemory.image_key = imgId;
        imageSaveMemory.imagePath = findImg;

        redirectAttributes.addAttribute("imgId", imgId);

        return "redirect:/yolo/img/check";
    }

    @GetMapping("/img/check")
    public String checkImg(Model model) {

        model.addAttribute("imgPath", imageSaveMemory.imagePath);

        return "yolo/ROI";
    }

    @GetMapping("/img/add")
    public String imgAdd(@ModelAttribute MedicineSaveForm medicineSaveForm, Model model) {

        model.addAttribute("medicineSaveForm", medicineSaveForm);
        model.addAttribute("imgPath", imageSaveMemory.imagePath);

        return "yolo/medicineForm";
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