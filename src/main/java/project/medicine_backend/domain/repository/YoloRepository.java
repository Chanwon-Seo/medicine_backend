package project.medicine_backend.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.medicine_backend.domain.entity.MedicineImage;

public interface YoloRepository extends JpaRepository<MedicineImage, Long> {

    public MedicineImage findByMemberId(Long memberId);
}
