package com.atom.artaccount.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.atom.artaccount.model.Reservation;
import com.atom.artaccount.repository.ReservationRepository;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Optional<Reservation> getReservationById(String id) {
        return reservationRepository.findById(id);
    }

    public Reservation createReservation(Reservation reservation) {
    	reservation.setDatesave();
    	reservation.setDateupdate();
        return reservationRepository.save(reservation);
    }

    public Reservation updateReservation(String id, Reservation reservationDetails) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow();
        reservation.setSend(reservationDetails.isSend());
        reservation.setDateupdate();
        return reservationRepository.save(reservation);
    }

    
    public void deleteReservation(String id) {
        reservationRepository.deleteById(id);
    }
    
    public Page<Reservation> getAll(Pageable pageable ){
    	return reservationRepository.findAll(pageable);
    }
    
    public List<Reservation> findBySend(boolean send){
		return reservationRepository.findBySend(send);
	}
	
    public Page<Reservation> findBySend(boolean send, Pageable pageable){
		return reservationRepository.findBySend(send, pageable);
	}
    
}
