package com.atom.artaccount.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atom.artaccount.log.UserActionLog;
import com.atom.artaccount.log.UserActionLogRepository;

@Service
public class UserLogService {

    @Autowired
    private UserActionLogRepository traceRepository;

    public UserActionLog saveUserActionLog(UserActionLog trace) {
        return traceRepository.save(trace);
    }

    public List<UserActionLog> getAllUserActionLogs() {
        return traceRepository.findAll();
    }

    public UserActionLog getUserActionLogById(String id) {
    	return null;
        //return traceRepository.findById(id).orElse(null);
    }
    
    public void deleteUserActionLog(UserActionLog trace) {
         traceRepository.delete(trace);
    }

    
    // Other service methods as needed
}