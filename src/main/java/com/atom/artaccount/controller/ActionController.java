package com.atom.artaccount.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.atom.artaccount.model.Action;
import com.atom.artaccount.service.ActionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin
public class ActionController {
    @Autowired
    private ActionService actionService;

	 @RequestMapping(value="/api/action1", method=RequestMethod.GET)
	    public MappingJacksonValue listAcheminement() {
		    Iterable<Action> acheminements = actionService.getAllActions();
	        MappingJacksonValue acheminementsFiltres = new MappingJacksonValue(acheminements);
	        return acheminementsFiltres;
	    }
	 
    @GetMapping("/api/action")
    public List<Action> getAllActions() {
    	
    	   ObjectMapper objectMapper = new ObjectMapper();
           try {
               String json = objectMapper.writeValueAsString(actionService.getAllActions());
       
               System.out.println(json);
           } catch (JsonProcessingException e) {
               e.printStackTrace();
           }
        return actionService.getAllActions();
    }

    @GetMapping("/api/action/{id}")
    public ResponseEntity<Action> getActionById(@PathVariable String id) {
        Optional<Action> action = actionService.getActionById(id);
        if (action.isPresent()) {
            return ResponseEntity.ok(action.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/api/action")
    public Action createAction(@RequestBody Action action) {
    	if(actionService.existsByCode(action.getCode())) {
    		return null;
    	}
        return actionService.createAction(action);
    }

    @PutMapping("/api/action/{id}")
    public ResponseEntity<Action> updateAction(@PathVariable String id, @RequestBody Action actionDetails) {
    	/*Optional<Action> actionsearch=actionService.findByNom(actionDetails.getNom());
    	System.out.println("################## "+actionsearch.isPresent());
    	if(actionsearch.isPresent()) {
    		if(!((actionsearch.get().getId()).equals(id))) {
    			return null;
    		}
    	}*/
    	
    	Optional<Action>  actionsearch=actionService.findByCode(actionDetails.getCode());
    	if(actionsearch.isPresent()) {
    		if(!((actionsearch.get().getId()).equals(id))) {
    			return null;
    		}
    	}
    	
    	Action updatedAction = actionService.updateAction(id, actionDetails);
    	
        return ResponseEntity.ok(updatedAction);
    }

    @DeleteMapping("/api/action/{id}")
    public ResponseEntity<Void> deleteAction(@PathVariable String id) {
        actionService.deleteAction(id);
        return ResponseEntity.noContent().build();
    }
    
	   @GetMapping(value="/api/action/{id}/feature")
	    public List<Action> listActionByFeature(@PathVariable("id") String id) {
		    List<Action> actions = actionService.getActionsByFeatureId(id);
	        //MappingJacksonValue actionsFiltres = new MappingJacksonValue(actions);
	        return actions;
	    }
}
