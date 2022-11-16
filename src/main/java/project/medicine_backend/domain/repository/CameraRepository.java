package project.medicine_backend.domain.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import project.medicine_backend.domain.entity.MedicineImage;

public interface CameraRepository extends JpaRepository<MedicineImage, Long> {

    public MedicineImage findByMember_Id(Long memberId);
}
