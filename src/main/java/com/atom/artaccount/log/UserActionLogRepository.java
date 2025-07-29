package com.atom.artaccount.log;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserActionLogRepository extends JpaRepository<UserActionLog, Long> {
	@Query("SELECT a FROM user_action_log a where (:username is null or a.username =:username) order by a.timestamp desc")
	Page<UserActionLog> findByAuditSearch(@Param("username") String username, Pageable pageable);
	
}
