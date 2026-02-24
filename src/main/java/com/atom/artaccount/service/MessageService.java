package com.atom.artaccount.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.atom.artaccount.model.Message;
import com.atom.artaccount.repository.MessageRepository;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;
    

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Optional<Message> getMessageById(String id) {
        return messageRepository.findById(id);
    }

    public Message createMessage(Message message) {
    	message.setDatesave();
    	message.setDateupdate();
        return messageRepository.save(message);
    }

    public Message updateMessage(String id, Message messageDetails) {
        Message message = messageRepository.findById(id).orElseThrow();
        message.setTelephone(messageDetails.getTelephone());
        message.setMessage(messageDetails.getMessage());
        message.setDateupdate();
        return messageRepository.save(message);
    }

    
    public void deleteMessage(String id) {
        messageRepository.deleteById(id);
    }
    
  
    
    public Page<Message> getAll(Pageable pageable ){
    	return messageRepository.findAll(pageable);
    }
}
