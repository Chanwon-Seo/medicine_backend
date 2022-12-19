package project.medicine_backend.domain.service;

import project.medicine_backend.domain.entity.Medicine;
import project.medicine_backend.domain.entity.MedicineImage;
import project.medicine_backend.domain.entity.Member;
import project.medicine_backend.domain.repository.MedicineRepository;
import project.medicine_backend.domain.repository.MemberRepository;
import project.medicine_backend.domain.repository.YoloRepository;
import project.medicine_backend.web.controller.yolo.form.MedicineSaveForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MedicineService {

    private final MedicineRepository medicineRepository;

    private final YoloRepository yoloRepository;

    private final MemberRepository memberRepository;

    @Transactional
    public void join(MedicineSaveForm medicineSaveForm, String username, Long image_path) {
        Member findMember = memberRepository.findChkByUserId(username);

        MedicineImage findMedicineImage = yoloRepository.findById(image_path).orElse(null);

        Medicine medicine = new Medicine(medicineSaveForm, findMedicineImage, findMember);

        medicineRepository.save(medicine);
    }

}