package project.medicine_backend.web.controller.camera.form;

import lombok.Data;

@Data
public class ImageSaveForm {

    private Long id;
    private String mimetype;
    private String original_name;
    private byte[] data;
    private String created;
}