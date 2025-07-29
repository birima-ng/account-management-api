package com.atom.artaccount.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.atom.artaccount.model.ProfileAction;

public interface ProfileActionRepository extends JpaRepository<ProfileAction, String>, JpaSpecificationExecutor<ProfileAction> {
	List<ProfileAction> findByProfileId(String profileId);
	List<ProfileAction> findByActionId(String actionId);
	List<ProfileAction> findByActionFeatureId(String featureId);
	List<ProfileAction> findByActionFeatureModuleId(String moduleId);
	boolean existsByProfileIdAndActionId(String profileId, String actionId);
	boolean existsByProfileIdAndActionIdAndAllowed(String profileId, String actionId, boolean allowed);
	@Query("SELECT p.action.id FROM profile_action p where p.profile.id=?1 and p.allowed=?2")
	List<String> findAllByProfileIdAndAllowed(String profileId, boolean allowed);
}