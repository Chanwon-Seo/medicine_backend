package project.medicine_backend.web.controller.camera.form;

import lombok.Data;

@Data
public class MedicineSaveForm {

    private Long id;

    private String medicineName;

    private String medicineDetail;

    private Integer medicineCount;

}
