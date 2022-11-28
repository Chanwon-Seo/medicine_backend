package project.medicine_backend.domain.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import project.medicine_backend.domain.entity.Medicine;

import java.util.List;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {

    List<Medicine> findByMemberId(Long userId);
}
