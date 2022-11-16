package project.medicine_backend.web.controller.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import project.medicine_backend.domain.service.MemberService;
import project.medicine_backend.web.controller.member.form.MemberForm;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/joinForm")
    public String joinForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "/auth/joinForm";
    }

    @PostMapping("/join")
    public String join(@Valid MemberForm form, BindingResult result) {

        if (!form.getPassword().equals(form.getPasswordChk())) {
            result.reject("pwNoEquals", "패스워드가 동일하지 않습니다.");
            return "/auth/joinForm";
        }

        if (result.hasErrors()) {
            return "/auth/joinForm";
        }

        memberService.join(form);
        return "/auth/loginForm";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "/auth/loginForm";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/info")
    public @ResponseBody String info() {
        return "개인정보";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')") // 외에는 접근이 불가능 함.
    @GetMapping("/data")
    public @ResponseBody String data() {
        return "개인정보";
    }

}
