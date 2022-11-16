package project.medicine_backend.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import project.medicine_backend.domain.entity.MedicineImage;
import project.medicine_backend.domain.entity.Member;
import project.medicine_backend.domain.repository.CameraRepository;
import project.medicine_backend.domain.repository.ImageSaveRepository;
import project.medicine_backend.domain.repository.MemberRepository;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CameraService {

    private final ImageSaveRepository imageSaveRepository;
    private final CameraRepository cameraRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public long join(MultipartFile file) throws IOException {

        String oriImgName = file.getOriginalFilename();
        String imgName = "";

//        String projectPath = "C:/Users/scwon/Desktop/spring_img/";
        String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/image/";
        UUID uuid = UUID.randomUUID();

        imgName = uuid + "_" + oriImgName; //파일명 -> imageName

        File saveFile = new File(projectPath, imgName);

        file.transferTo(saveFile);

        Member findMember = memberRepository.findChkByUserId("admin");

        MedicineImage medicineImage = new MedicineImage();
        medicineImage.setMedicineImagePath(imgName);
        medicineImage.setMember(findMember);

        cameraRepository.save(medicineImage);

        long saveImageMapId = imageSaveRepository.save(imgName);

        log.info("saveImageMapId.getId={}", saveImageMapId);

        return saveImageMapId;
    }

    public String findImage(long imgId) {
        return imageSaveRepository.findId(imgId);
    }

}
