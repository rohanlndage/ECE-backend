package com.example.ECECommunity.service;
import com.example.ECECommunity.entity.Member;
import com.example.ECECommunity.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member addMember(Member member) {
        return memberRepository.save(member);
    }
}