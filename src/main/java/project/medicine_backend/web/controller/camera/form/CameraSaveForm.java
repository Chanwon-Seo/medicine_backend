package project.medicine_backend.web.controller.camera.form;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CameraSaveForm {

    private Double X;
    private Double Y;
    private Double W;
    private Double H;

    private LocalDateTime dateTime;

    public CameraSaveForm(Double x, Double y, Double w, Double h, LocalDateTime dateTime) {
        X = x;
        Y = y;
        W = w;
        H = h;
        this.dateTime = dateTime;
    }
}
