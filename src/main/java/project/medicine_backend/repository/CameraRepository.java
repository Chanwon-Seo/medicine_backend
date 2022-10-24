package project.medicine_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.medicine_backend.domain.Camera;

public interface CameraRepository extends JpaRepository<Camera, Long> {
}
