package com.isfive.usearth.domain.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isfive.usearth.domain.member.entity.Member;
import com.isfive.usearth.exception.EntityNotFoundException;
import com.isfive.usearth.exception.ErrorCode;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

	Optional<Member> findByUsername(String username);

	Optional<Member> findByEmail(String email);

	Optional<Member> findByUsernameAndPassword(String username, String password);

	default Member findByUsernameOrThrow(String username) {
		return findByUsername(username).orElseThrow(
			() -> new EntityNotFoundException(ErrorCode.MEMBER_NOT_FOUND));
	}

	default Member findByEmailOrThrow(String email) {
		return findByEmail(email).orElseThrow(
			() -> new EntityNotFoundException(ErrorCode.MEMBER_NOT_FOUND));
	}

	default Member findByUsernameAndPasswordOrThrow(String username, String password) {
		return findByUsernameAndPassword(username, password).orElseThrow(
			() -> new EntityNotFoundException(ErrorCode.MEMBER_NOT_FOUND));
	}

	void deleteByUsername(String username);

	default void deleteByUsernameOrThrow(String username) {
		if (existsByUsername(username))
			deleteByUsername(username);
		else
			throw new EntityNotFoundException(ErrorCode.MEMBER_NOT_FOUND);
	}

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);
}
