package com.mypocketmon;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mypocketmon.aop.TimeTraceAop;
import com.mypocketmon.repository.MemberRepository;
import com.mypocketmon.service.MemberService;

@Configuration
public class SpringConfig {
	
	private final MemberRepository memberRepository;
	
	@Autowired
	public SpringConfig(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository);
	}
	
	/*
	@Bean
	public TimeTraceAop timeTraceAop() {
		return new TimeTraceAop();
	}
	
	private final DataSource dataSource;
	private EntityManager em;
	
	public SpringConfig(DataSource dataSource, EntityManager em) {
		this.dataSource = dataSource;
		this.em = em;
	}

	@Bean
	public MemberRepository memberRepository() {
		// return new MemoryMemberRepository();
		// return new JdbcMemberRepository(dataSource);
		// return new JdbcTemplateMemberRepository(dataSource);
		// return new JpaMemberRepository(em);
	}
	*/

}
