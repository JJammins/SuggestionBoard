package com.mysite.suggestion.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<SiteUser, Long>{
	Optional<SiteUser> findByUserID(String userID);
}
