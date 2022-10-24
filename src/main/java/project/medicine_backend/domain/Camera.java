package project.medicine_backend.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import project.medicine_backend.web.controller.camera.form.CameraSaveForm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Camera {

    @Id
    @GeneratedValue
    @Column(name = "camera_id")
    private Long id;

    private Double X;
    private Double Y;
    private Double W;
    private Double H;

    private LocalDateTime dateTime;

    public Camera(CameraSaveForm form) {
        this.setX(form.getX());
        this.setY(form.getY());
        this.setW(form.getW());
        this.setH(form.getH());
        this.setDateTime(LocalDateTime.now());
    }
}
