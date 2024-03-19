package org.example.project0316.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class CustomMemberDetails implements UserDetails {

    private Member member;


    public CustomMemberDetails (Member member){
        this.member = member;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { // 사용자의 특정한 권한에 대해서 리턴해주는 메서드, 즉 role 값

        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {

                return member.getRole(); // role 값 리턴
            }
        });

        return collection;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() { // 사용자 id가 만료 되었는가?
        return true;
    }

    @Override
    public boolean isAccountNonLocked() { // 사용자 id가 잠겨있는가?
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() { //사용자 id가 활성화되었는가?
        return true;
    }

    // 4개의 필드가 도메인에 존재하지 않기 때문에 그냥 true 처리 해준다. 구현하고 싶으면 도메인에 필드 추가를 해주면 된다.
}
