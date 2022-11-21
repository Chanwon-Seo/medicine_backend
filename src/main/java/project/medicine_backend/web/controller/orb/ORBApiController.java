package project.medicine_backend.web.controller.orb;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.medicine_backend.config.auth.PrincipalDetails;
import project.medicine_backend.domain.service.OrbService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Controller
@RequestMapping("/orb")
@RequiredArgsConstructor
public class ORBApiController {

    private final OrbService orbService;

    UserSaveMemory userSaveMemory = new UserSaveMemory();

    @GetMapping("/stream")
    public String orb_stream(@AuthenticationPrincipal PrincipalDetails principalDetails) {

        userSaveMemory.username = principalDetails.getUsername();

        log.info("orb 스트리밍 중");
        return "orb/orbStreamMain";
    }


    @PostMapping("/img")
    @ResponseBody
    public ResponseEntity<Resource> imgPUT() throws IOException {

        String sessionUsername = userSaveMemory.username;
        log.info("sessionUsername = {}", sessionUsername);

        String finalImagePath = orbService.findMedicineImage(sessionUsername);
        log.info("finalImagePath = {}", finalImagePath);

        FileSystemResource resource = new FileSystemResource(finalImagePath);
        log.info("resource@@@@ = {}", resource);

        if (!resource.exists()) {
            log.info("에러에러에러 file404");
            throw new IOException();
        }
        HttpHeaders header = new HttpHeaders();
        Path filePath = null;


        filePath = Paths.get(finalImagePath);
        header.add("Content-Type", Files.probeContentType(filePath));

        log.info("probeContentType ={}", Files.probeContentType(filePath));
        log.info("response entity OK");

        return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
    }


    @GetMapping("/recognitionJet")
    public void takingMedicineJet(@RequestParam String result) {
        log.info("asdfasdfasdf = {}", result);

    }

    @GetMapping("/recognitionClient")
    public String takingMedicine() {
        return "orb/orbTakingMedicine";
    }


}

class UserSaveMemory {
    public String username;

    public UserSaveMemory() {

    }
}