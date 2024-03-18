package org.example.project0316.service;

import org.example.project0316.domain.Member;
import org.example.project0316.dto.JoinDto;
import org.example.project0316.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public void joinMember (JoinDto joinDto) { // Dto를 받아서 레포지토리에 저장하는 메서드


        //db에 이미 동일한 username을 가진 회원이 존재하는지?
        boolean isMember = memberRepository.existsByUsername(joinDto.getUsername());
        if (isMember) {
            return;
        }

        Member member = new Member();

        member.setUsername(joinDto.getUsername());
        member.setPassword(bCryptPasswordEncoder.encode(joinDto.getPassword())); // 암호화 진행해서 비밀번호 설정
        member.setRole("ROLE_USER"); // 유저로 세팅


        memberRepository.save(member);
    }
}