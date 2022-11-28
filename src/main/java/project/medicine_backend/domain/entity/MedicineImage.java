package project.medicine_backend.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class MedicineImage {

    @Id
    @GeneratedValue
    @Column(name = "medicineImage_id")
    private Long id;

    private String medicineImagePath;

    @OneToOne(mappedBy = "medicineImage")
    private Medicine medicine;

}
