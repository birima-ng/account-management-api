package com.atom.artaccount.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.atom.artaccount.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, String>, JpaSpecificationExecutor<Reservation> {
	
	List<Reservation> findBySend(boolean send);
	Page<Reservation> findBySend(boolean send, Pageable pageable);

}