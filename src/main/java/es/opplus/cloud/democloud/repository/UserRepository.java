package es.opplus.cloud.democloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.opplus.cloud.democloud.domain.UserEntity;


public interface UserRepository extends JpaRepository<UserEntity, Long> {

	
	
}

