package com.cos.photogramstart.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

//어노테이션이 없어도 JPARepository상속을 받으면 IoC 등록이 자동으로 된다
public interface UserRepository extends JpaRepository<User, Integer>{
	// JPA query creation method
	User findByUsername(String username);
}
