package org.example.project0316.controller;

import org.example.project0316.dto.JoinDto;
import org.example.project0316.service.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class JoinController {

    @Autowired
    private JoinService joinService;

    @GetMapping("/join")
    public String joinP(){
        return "join";
    }

    @PostMapping("/joinProc")
    public String joinProcess(JoinDto joinDto) { // 이 경로로 오면 로그인으로 리다이렉트 한다.

        System.out.println(joinDto.getUsername());

        joinService.joinMember(joinDto);

        return "redirect:/login";
    }
}
