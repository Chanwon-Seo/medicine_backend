package project.medicine_backend.web.controller.orb;

import project.medicine_backend.config.auth.PrincipalDetails;
import project.medicine_backend.domain.entity.Medicine;
import project.medicine_backend.domain.service.OrbService;
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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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
    public String imgPUT() throws IOException {

        String sessionUsername = userSaveMemory.username;
        log.info("sessionUsername = {}", sessionUsername);

        List<Medicine> findAllMedicine = orbService.findMedicineImageV2(sessionUsername);

        log.info("findAllMedicine = {}", findAllMedicine.size());

        String str = "";


        for (int i = 0; i < findAllMedicine.size(); i++) {
            if (findAllMedicine.size() == 1) {
                str += findAllMedicine.get(i).getMedicineImage().getMedicineImagePath();
            } else if (findAllMedicine.size() == i + 1) {
                str += findAllMedicine.get(i).getMedicineImage().getMedicineImagePath();
            } else {
                str += findAllMedicine.get(i).getMedicineImage().getMedicineImagePath() + ",";
            }
        }

        return str;
    }

    @PostMapping("/api/download/{imageName}")
    @ResponseBody
    public ResponseEntity<Resource> imageDownload(@PathVariable String imageName) throws IOException {

        String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/image/";
        String finalImagePath = projectPath + imageName;
        FileSystemResource resource = new FileSystemResource(finalImagePath);

        log.info("resource@@@@@@@@ = {}", imageName);
        log.info("resource@@@@ = {}", resource);

        if (!resource.exists()) {
            throw new IOException();
        }

        HttpHeaders header = new HttpHeaders();
        Path filePath = null;

        filePath = Paths.get(finalImagePath);
        header.add("Content-Type", Files.probeContentType(filePath));

        log.info("probeContentType ={}", Files.probeContentType(filePath)); //image/jpeg
        log.info("response entity OK");

        return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
    }


    @GetMapping("/recognitionJet")
    public void takingMedicineJet(@RequestParam String result) {
        log.info("recognitionJet입니다 = {}", result);
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