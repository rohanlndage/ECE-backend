package com.example.ECECommunity.controller;
import com.example.ECECommunity.entity.Member;
import com.example.ECECommunity.service.MemberService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
@CrossOrigin(origins = "*")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public Member addMember(@RequestBody Member member) {
        return memberService.addMember(member);
    }
}