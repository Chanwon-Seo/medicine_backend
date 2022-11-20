package project.medicine_backend.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import project.medicine_backend.domain.entity.MedicineImage;
import project.medicine_backend.domain.entity.Member;
import project.medicine_backend.domain.repository.ImageSaveRepository;
import project.medicine_backend.domain.repository.MemberRepository;
import project.medicine_backend.domain.repository.YoloRepository;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class YoloService {

    private final ImageSaveRepository imageSaveRepository;
    private final YoloRepository yoloRepository;
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

        yoloRepository.save(medicineImage);

        long saveImageMapId = imageSaveRepository.save(imgName);

        log.info("saveImageMapId.getId={}", saveImageMapId);

        return saveImageMapId;
    }

    public String findImage(long imgId) {
        return imageSaveRepository.findId(imgId);
    }
/*
    public String join(MultipartFile files) throws IOException {

        ImageSaveForm form = new ImageSaveForm();
        form.setMimetype(files.getContentType());
        form.setOriginal_name(files.getOriginalFilename());
        form.setData(files.getBytes());

        String mapNum = String.valueOf(cameraRepository.save(form));

        return mapNum;
    }

 */
//
//    public String join(MultipartFile files) throws IOException {
//
//        ImageSaveForm form = new ImageSaveForm();
//        form.setMimetype(files.getContentType());
//        form.setOriginal_name(files.getOriginalFilename());
//        form.setData(files.getBytes());
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Type", form.getMimetype());
//        headers.add("Content-Length", String.valueOf(form.getData().length));
//
//        cameraRepository.save(form);
//
//        return Base64Utils.encodeToString(files.getBytes()); //base64로 인코딩한 값
//    }


}
