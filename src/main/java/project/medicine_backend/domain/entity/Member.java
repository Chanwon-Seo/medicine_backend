package project.medicine_backend.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import project.medicine_backend.web.controller.member.form.MemberForm;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String username;
    private String userId;
    private String userPw;

    private String role; ///ROLE_USER, ROLE_ADMIN

    @OneToMany(mappedBy = "member")
    private List<MedicineImage> medicineImages = new ArrayList<>();


    public Member(MemberForm form) {
        this.userId = form.getUserId();
        this.username = form.getUsername();
        this.userPw = form.getPassword();
    }

    public Member(String username, String userId, String userPw, String role) {
        this.username = username;
        this.userId = userId;
        this.userPw = userPw;
        this.role = role;
    }
}

