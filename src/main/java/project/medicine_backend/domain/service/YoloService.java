package project.medicine_backend.domain.service;

import project.medicine_backend.domain.entity.MedicineImage;
import project.medicine_backend.domain.repository.ImageSaveRepository;
import project.medicine_backend.domain.repository.YoloRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class YoloService {

    private final YoloRepository yoloRepository;

    @Transactional
    public Long join(MultipartFile file) throws IOException {

        String oriImgName = file.getOriginalFilename();
        String imgName = "";

        String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/image/";
        UUID uuid = UUID.randomUUID();

        imgName = uuid + "_" + oriImgName; //파일명 -> imageName

        File saveFile = new File(projectPath, imgName);

        //이미지 저장
        file.transferTo(saveFile);

        MedicineImage medicineImage = new MedicineImage();
        medicineImage.setMedicineImagePath(imgName);

        MedicineImage saveMedicineImage = yoloRepository.save(medicineImage);

        log.info("saveMedicineImage = {} ", saveMedicineImage);

        Long saveImageMapId = ImageSaveRepository.save(saveMedicineImage.getId());
        log.info("saveMedicineImage.getId() ={}", saveMedicineImage.getId());

        return saveImageMapId;
    }

    public Long findImage(Long imgId) {
        return ImageSaveRepository.findId(imgId);
    }

    public String findImagePath(Long imgId) {
        MedicineImage medicineImage = yoloRepository.findById(imgId).orElse(null);
        return medicineImage.getMedicineImagePath();
    }


}
