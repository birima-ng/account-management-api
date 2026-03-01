package com.atom.artaccount.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.atom.artaccount.model.MessageBienvenue;
import com.atom.artaccount.repository.MessageBienvenueRepository;

@Service
public class MessageBienvenueService {
    @Autowired
    private MessageBienvenueRepository messagebienvenueRepository;
    

    public List<MessageBienvenue> getAllMessageBienvenues() {
        return messagebienvenueRepository.findAll();
    }

    public Optional<MessageBienvenue> getMessageBienvenueById(String id) {
        return messagebienvenueRepository.findById(id);
    }

    public MessageBienvenue createMessageBienvenue(MessageBienvenue messagebienvenue) {
    	messagebienvenue.setDatesave();
    	messagebienvenue.setDateupdate();
        return messagebienvenueRepository.save(messagebienvenue);
    }

    public MessageBienvenue updateMessageBienvenue(String id, MessageBienvenue messagebienvenueDetails) {
        MessageBienvenue messagebienvenue = messagebienvenueRepository.findById(id).orElseThrow();
        messagebienvenue.setDateupdate();
        return messagebienvenueRepository.save(messagebienvenue);
    }

    
    public void deleteMessageBienvenue(String id) {
        messagebienvenueRepository.deleteById(id);
    }
    
    
    public boolean existsToday(String telephone) {
    	
    	LocalDate today = LocalDate.now();

    	LocalDateTime startOfDay = today.atStartOfDay();
    	LocalDateTime endOfDay = today.atTime(23, 59, 59);

    	Date startDate = Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
    	Date endDate = Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
    	
    	List<MessageBienvenue> list = messagebienvenueRepository.findToday(startDate, endDate,telephone);
    	
    	if(list.size()>0) {
    		return true;
    	}else {
    		
    		MessageBienvenue messagebienvenue = new  MessageBienvenue();
    		messagebienvenue.setTelephone(telephone);
    		messagebienvenue.setDatesave();
        	messagebienvenue.setDateupdate();
            messagebienvenueRepository.save(messagebienvenue);
            
    		return false;
    	}
    	
    }

}
