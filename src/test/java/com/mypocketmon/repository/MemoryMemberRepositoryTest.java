package com.mypocketmon.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

import com.mypocketmon.domain.Member;

class MemoryMemberRepositoryTest {
	
	MemoryMemberRepository repository = new MemoryMemberRepository();
	
	@AfterEach
	public void afterEach() {
		repository.clearStore();
	}
	
	@Test
	public void save() {
		Member member = new Member();
		member.setName("Spring");
		
		repository.save(member);
		Member result = repository.findById(member.getId()).get();
		assertThat(member).isEqualTo(result);
	}
	
	@Test
	public void findByName() {
		Member member1 = new Member();
		member1.setName("Spring1");
		repository.save(member1);
		
		Member member2 = new Member();
		member2.setName("Spring2");
		repository.save(member2);
		
		Member result = repository.findByName("Spring1").get();
		
		assertThat(result).isEqualTo(member1);
	}

	@Test
	public void findAll() {
		Member member1 = new Member();
		member1.setName("Spring1");
		repository.save(member1);
		
		Member member2 = new Member();
		member2.setName("Spring2");
		repository.save(member2);
		
		List<Member> result = repository.findAll();
		
		assertThat(result.size()).isEqualTo(2);
	}
	
}
