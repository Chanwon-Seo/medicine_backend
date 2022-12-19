package project.medicine_backend.domain.entity;

import project.medicine_backend.web.controller.yolo.form.MedicineSaveForm;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Medicine {

    @Id
    @GeneratedValue
    @Column(name = "medicine_id")
    private Long id;

    private String medicineName;

    private String medicineDetail;

    private Integer medicineCount;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "medicineImage_id")
    private MedicineImage medicineImage;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDateTime registration_time;

    public Medicine() {

    }

    public Medicine(MedicineSaveForm form, MedicineImage medicineImage, Member findMember) {
        this.medicineName = form.getMedicineName();
        this.medicineDetail = form.getMedicineDetail();
        this.medicineCount = form.getMedicineCount();
        this.registration_time = LocalDateTime.now();
        this.medicineImage = medicineImage;
        this.member = findMember;
    }

}
