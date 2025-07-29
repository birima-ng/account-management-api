package com.atom.artaccount.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.atom.artaccount.model.Region;

@Repository
public interface RegionRepository extends JpaRepository <Region, String>  {
	List<Region> findByPaysId(String regionId);
	/*@Query("SELECT r FROM region r JOIN FETCH r.pays p where r.id=?1")
	Region findById(String id);
	
	@Query("SELECT r FROM region r JOIN FETCH r.pays p ")
	List<Region> findAllJoin();
	*/
	
	@Query("SELECT r FROM region r where r.id=?1")
	List<Region> findByIdRegionList(String id);
}
