package project.medicine_backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.medicine_backend.domain.Camera;
import project.medicine_backend.repository.CameraRepository;
import project.medicine_backend.web.controller.camera.form.CameraSaveForm;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CameraService {

    private final CameraRepository cameraRepository;

    @Transactional
    public Camera join(CameraSaveForm form) {
        Camera camera = new Camera(form);

        Camera saveCamera = cameraRepository.save(camera);
        return saveCamera;
    }
    
}
