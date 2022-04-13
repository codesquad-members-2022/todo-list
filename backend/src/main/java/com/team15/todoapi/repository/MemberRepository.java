package com.team15.todoapi.repository;

import com.team15.todoapi.domain.Member;

public interface MemberRepository {

	Member findByUserId(String userId);

}
