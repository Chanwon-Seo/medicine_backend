package project.medicine_backend.web.controller.camera;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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

    @GetMapping("/add")
    public String videoGET() {
        log.info("이미지 스트리밍 중입니다.");
        return "video/streamMain";
    }

    @GetMapping("/check")
    public String videoBy(@RequestParam String path,
                          @RequestParam String filename) {
        log.info("인식된 약통 화면입니다. path는 ={}", path);
        log.info("인식된 약통 화면입니다. filename는 ={}", filename);
        return "video/ROI";
    }

    @GetMapping("/img/upload")
    public String newFile() {
        return "video/upload-form";
    }

    @PostMapping("/img/upload")
    public ResponseEntity<byte[]> handleFileUpload(@RequestParam("check_img") MultipartFile files) throws IOException {

        ImageVO imageVO = new ImageVO();
        imageVO.setMimetype(files.getContentType());
        imageVO.setOriginal_name(files.getOriginalFilename());
        imageVO.setData(files.getBytes());
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", imageVO.getMimetype());
        headers.add("Content-Length", String.valueOf(imageVO.getData().length));
        return new ResponseEntity<byte[]>(imageVO.getData(), headers, HttpStatus.OK);
    }

}
