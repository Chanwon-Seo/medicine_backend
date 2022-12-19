package project.medicine_backend.domain.repository;


import project.medicine_backend.domain.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {

//    public Medicine findByMedicineImageMemberId(Long userId);

    List<Medicine> findByMemberId(Long userId);
}
