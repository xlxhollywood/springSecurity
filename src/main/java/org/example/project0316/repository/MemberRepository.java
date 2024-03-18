package org.example.project0316.repository;

import org.example.project0316.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {

    boolean existsByUsername(String username);
}
