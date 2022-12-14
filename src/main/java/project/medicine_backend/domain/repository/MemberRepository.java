package project.medicine_backend.domain.repository;


import project.medicine_backend.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUserId(String userId);

    public Member findChkByUserId(String userId);

}
