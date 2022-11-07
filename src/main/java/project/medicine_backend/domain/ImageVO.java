package project.medicine_backend.domain;

import lombok.Data;

@Data
public class ImageVO {

    private String mimetype;
    private String original_name;
    private byte[] data;
    private String created;
}