package com.example.ECECommunity.repository;

import com.example.ECECommunity.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}