package com.mypocketmon.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mypocketmon.domain.Member;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository{

	// JPQL select m from Member m where m.name = ? 로 만들어줌.
	@Override
	Optional<Member> findByName(String name);
}
