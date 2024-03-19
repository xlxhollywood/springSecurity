package org.example.project0316.service;

import org.example.project0316.domain.CustomMemberDetails;
import org.example.project0316.domain.Member;
import org.example.project0316.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomMemberDetailService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = memberRepository.findByUsername(username);

        // member == null이면 멤버가 없다는 뜻 , 그냥 Null 반환
        if(member != null ) {

            return new CustomMemberDetails(member); // 생성자 방식으로 리턴하기 때문에 생성자 만들어줘야함
        }


        return null;
    }
}
