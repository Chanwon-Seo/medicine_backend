package project.medicine_backend.web.controller.orb.form;


import lombok.Data;
import project.medicine_backend.domain.entity.Medicine;

import java.util.ArrayList;
import java.util.List;

@Data
public class ImageResponseForm {

    private List<String> imageOriginalPath = new ArrayList<>();

    public ImageResponseForm(List<Medicine> findAllMedicine) {
        for (int i = 0; i < findAllMedicine.size(); i++) {
            imageOriginalPath.add(findAllMedicine.get(i).getMedicineImage().getMedicineImagePath());
        }
    }
}
