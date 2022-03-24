package com.mypocketmon.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mypocketmon.domain.Member;
import com.mypocketmon.repository.MemberRepository;

@Transactional
public class MemberService {

	private final MemberRepository memberRepository;
	
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
		// 의존관계가 실행중에 동적으로 변하는 경우는 거의 없으므로 생성자 주입을 권장한다.
	}
	
	/*
	 * 회원가입
	 */
	public Long join(Member member) {
		
		validateDuplicationMember(member);
		memberRepository.save(member);
		return member.getId();
	}
		
	/*
	 * 전체 회원 조회
	 */
	public List<Member> findMembers(){
		return memberRepository.findAll();
	}
	 
	/*
	 * 개발 회원 조회
	 */
	public Optional<Member> findOne(Long memberId){
		return memberRepository.findById(memberId);
	}
	
	
	/*
	 * 중복 회원 검증
	 */
	private void validateDuplicationMember(Member member) {
		memberRepository.findByName(member.getName())
			.ifPresent(m ->{
				throw new IllegalStateException("이미 존재하는 회원입니다.");
			});
	}
}
