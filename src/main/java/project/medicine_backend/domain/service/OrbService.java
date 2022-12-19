package project.medicine_backend.domain.service;

import project.medicine_backend.domain.entity.Medicine;
import project.medicine_backend.domain.entity.Member;
import project.medicine_backend.domain.repository.MedicineRepository;
import project.medicine_backend.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrbService {

    private final MedicineRepository medicineRepository;

    private final MemberRepository memberRepository;

//    public String findMedicineImageV1(String sessionUsername) {
//
//        Member findMember = memberRepository.findChkByUserId(sessionUsername);
//        Medicine findMedicine = medicineRepository.findByMedicineImageMemberId(findMember.getId());
//
//        String findMedicineImagePath = findMedicine.getMedicineImage().getMedicineImagePath();
//        String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/image/";
//
//
//        return projectPath + findMedicineImagePath;
//    }

    public List<Medicine> findMedicineImageV2(String sessionUsername) {

        Member findMember = memberRepository.findChkByUserId(sessionUsername);
        List<Medicine> findAllMedicine = medicineRepository.findByMemberId(findMember.getId());

//        String findMedicineImagePath = findAllMedicine.get(0).getMedicineImage().getMedicineImagePath();
//        String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/image/";
//
//
//        return projectPath + findMedicineImagePath;
        return findAllMedicine;
    }

}
