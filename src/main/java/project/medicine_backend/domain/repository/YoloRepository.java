package project.medicine_backend.domain.repository;

import project.medicine_backend.domain.entity.MedicineImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface YoloRepository extends JpaRepository<MedicineImage, Long> {}
