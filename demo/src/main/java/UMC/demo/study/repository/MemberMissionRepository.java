package UMC.demo.study.repository;

import UMC.demo.study.domain.Member;
import UMC.demo.study.domain.mapping.MemberMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    Page<MemberMission> findAllByMember(Member member, PageRequest pageRequest);
}
