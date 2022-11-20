package project.medicine_backend.web.controller.yolo.form;

import lombok.Data;

@Data
public class ImagePathForm {

    private String filepath;

    public ImagePathForm(String filepath) {
        this.filepath = filepath;
    }
}
