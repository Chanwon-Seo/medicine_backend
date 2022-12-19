package project.medicine_backend.domain.service;

import project.medicine_backend.domain.entity.Member;
import project.medicine_backend.domain.repository.MemberRepository;
import project.medicine_backend.web.controller.member.form.MemberForm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MemberRepository memberRepository;

    @Transactional
    public void join(MemberForm form) {

        validateDuplicateMember(form); //중복 회원 검증

        Member member = new Member(form);

        member.setRole("ROLE_USER"); //강제 삽입
        String encPassword = bCryptPasswordEncoder.encode(form.getPassword());
        member.setUserPw(encPassword);


        memberRepository.save(member);
    }

    private void validateDuplicateMember(MemberForm form) {

        Optional<Member> findUserId = memberRepository.findByUserId(form.getUserId());

        if (!findUserId.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다"); //프론트
        }
    }

}
