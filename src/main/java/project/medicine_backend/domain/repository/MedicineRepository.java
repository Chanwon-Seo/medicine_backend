package project.medicine_backend.domain.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import project.medicine_backend.domain.entity.Medicine;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {


    public Medicine findByMedicineImageMemberId(Long userId);
}
