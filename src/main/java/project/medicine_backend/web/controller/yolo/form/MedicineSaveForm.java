package project.medicine_backend.web.controller.yolo.form;

import lombok.Data;

@Data
public class MedicineSaveForm {

    private Long id;

    private String medicineName;

    private String medicineDetail;

    private Integer medicineCount;

}
