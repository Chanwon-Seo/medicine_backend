package project.medicine_backend.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.medicine_backend.domain.entity.Medicine;
import project.medicine_backend.domain.entity.MedicineImage;
import project.medicine_backend.domain.entity.Member;
import project.medicine_backend.domain.repository.CameraRepository;
import project.medicine_backend.domain.repository.MedicineRepository;
import project.medicine_backend.domain.repository.MemberRepository;
import project.medicine_backend.web.controller.camera.form.MedicineSaveForm;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MedicineService {

    private final MedicineRepository medicineRepository;
    private final CameraRepository cameraRepository;

    private final MemberRepository memberRepository;

    @Transactional
    public void join(MedicineSaveForm medicineSaveForm, String username) {
        Member findMember = memberRepository.findChkByUserId(username);
        MedicineImage findMedicineImage = cameraRepository.findByMember_Id(findMember.getId());

        Medicine medicine = new Medicine(medicineSaveForm, findMedicineImage);

        medicineRepository.save(medicine);
    }

}