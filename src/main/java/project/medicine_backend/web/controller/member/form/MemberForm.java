package project.medicine_backend.web.controller.member.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class MemberForm {

    //회원이름
    @NotEmpty(message = "정보를 입력해주세요")
    private String username;

    //회원정보
    @NotEmpty(message = "정보를 입력해주세요")
    private String userId;

    @NotEmpty(message = "정보를 입력해주세요")
    private String password;

    @NotEmpty(message = "정보를 입력해주세요")
    private String passwordChk;
}


