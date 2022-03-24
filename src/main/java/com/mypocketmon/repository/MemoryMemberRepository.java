package com.mypocketmon.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.mypocketmon.domain.Member;

//@Repository
/**
* 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
*/
public class MemoryMemberRepository implements MemberRepository{
	
	private static Map<Long, Member> store = new HashMap<>();
	private static long sequence = 0L;

	@Override
	public Member save(Member member) {
		// TODO Auto-generated method stub
		member.setId(++sequence);
		store.put(member.getId(), member);
		return member;
	}

	@Override
	public Optional<Member> findById(Long id) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(store.get(id));
	}

	@Override
	public Optional<Member> findByName(String name) {
		// TODO Auto-generated method stub
		return store.values().stream()
							.filter(member -> member.getName().equals(name))
							.findAny();
	}

	@Override
	public List<Member> findAll() {
		// TODO Auto-generated method stub
		return new ArrayList<>(store.values());
	}
	
	public void clearStore() {
		store.clear();
	}

}
