package project.medicine_backend.web.controller.camera;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import project.medicine_backend.domain.ImageVO;

import java.io.IOException;


@Slf4j
@Controller
@RequestMapping("/camera")
public class CameraApiController {

    private String keyString;

    @GetMapping("/add")
    public String videoGET() {
        log.info("이미지 스트리밍 중입니다.");
        return "video/streamMain";
    }

    //    @GetMapping("/img/upload")
    public String testimg() {
        return "video/upload-form";
    }

    @PostMapping("/img/upload")
    public void handleFileUploadV2(@RequestParam("check_img") MultipartFile files) throws IOException {
        log.info("이미지를 저장해야합니다.");
        ImageVO imageVO = new ImageVO();
        imageVO.setMimetype(files.getContentType());
        imageVO.setOriginal_name(files.getOriginalFilename());
        imageVO.setData(files.getBytes());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", imageVO.getMimetype());
        headers.add("Content-Length", String.valueOf(imageVO.getData().length));

        String keyString = Base64Utils.encodeToString(files.getBytes()); //base64로 인코딩한 값

//        Member member = new Member();
//        member.setKeyString(keyString);

        log.info("keyString@@@@@ ={}", keyString);
    }

    @GetMapping("/testtest/img")
    public String tesetestset(Model model) {

        log.info("testest keyString={}", keyString);
        log.info("tesetestset일로 왔음");

        model.addAttribute("fileContent", keyString);
        return "video/ROI";
    }
}