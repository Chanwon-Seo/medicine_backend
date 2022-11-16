package project.medicine_backend.web.controller.camera.form;

import lombok.Data;

@Data
public class ImagePathForm {

    private String filepath;

    public ImagePathForm(String filepath) {
        this.filepath = filepath;
    }
}
