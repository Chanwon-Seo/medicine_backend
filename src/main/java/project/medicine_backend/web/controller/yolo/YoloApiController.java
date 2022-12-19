package project.medicine_backend.web.controller.yolo;

import project.medicine_backend.config.auth.PrincipalDetails;
import project.medicine_backend.domain.repository.ImageSaveRepository;
import project.medicine_backend.domain.service.MedicineService;
import project.medicine_backend.domain.service.YoloService;
import project.medicine_backend.web.controller.yolo.form.MedicineSaveForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/yolo")
@RequiredArgsConstructor
public class YoloApiController {
    private final YoloService yoloService;

    private final MedicineService medicineService;

    private final ImageSaveRepository imageSaveRepository;

    ImageSaveMemory imageSaveMemory = new ImageSaveMemory();

    @GetMapping("/add")
    public String videoGET() {
        log.info("이미지 스트리밍 중입니다.");
        return "yolo/yoloStreamMain";
    }

    @PostMapping("/img/upload")
    public ResponseEntity<Resource> handleFileUpload(@RequestPart("check_img") MultipartFile files, RedirectAttributes redirectAttributes) throws IOException {
        Long imgId = yoloService.join(files);

        log.info("imgID=123123 ={} ", imgId);

        Long findImg = yoloService.findImage(imgId);
        imageSaveMemory.image_key = imgId;
        imageSaveMemory.imagePath = findImg;

        redirectAttributes.addAttribute("imgId", imgId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/img/check")
    public String checkImg(Model model) {

        Long findImageId = imageSaveRepository.findById(imageSaveMemory.image_key);

        String findImagePath = yoloService.findImagePath(findImageId);

        model.addAttribute("imgPath", findImagePath);

        return "yolo/ROI";
    }

    @GetMapping("/img/add")
    public String imgAdd(@ModelAttribute MedicineSaveForm medicineSaveForm, Model model) {

        Long findImageId = imageSaveRepository.findById(imageSaveMemory.image_key);

        String findImagePath = yoloService.findImagePath(findImageId);

        model.addAttribute("medicineSaveForm", medicineSaveForm);
        model.addAttribute("imgPath", findImagePath);

        return "yolo/medicineForm";
    }

    @PostMapping("/img/add")
    public String imgSave(@ModelAttribute MedicineSaveForm medicineSaveForm, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        medicineService.join(medicineSaveForm, principalDetails.getUsername(), imageSaveMemory.imagePath);
        return "redirect:/main";
    }

}

class ImageSaveMemory {
    public Long image_key;
    public Long imagePath;

    public ImageSaveMemory() {
    }

}