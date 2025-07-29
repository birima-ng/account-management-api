package com.atom.artaccount.controller;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.atom.artaccount.Tools;
import com.atom.artaccount.dto.MenuItem;
import com.atom.artaccount.dto.ModuleDTO;
import com.atom.artaccount.model.Module;
import com.atom.artaccount.model.User;
import com.atom.artaccount.service.ModuleService;
import com.atom.artaccount.service.UserService;


@RestController
@CrossOrigin
public class ModuleController {
	
    @Autowired
    private ModuleService moduleService;
    
    @Autowired
    private UserService userService;

    @GetMapping("/api/module")
    public List<Module> getAllModules() {
    	User user = Tools.getUser(userService);
        //return moduleService.getAllModules();
        return moduleService.findBySystemeId(user.getSysteme().getId());
    }

    @GetMapping("/api/module/{id}")
    public ResponseEntity<Module> getModuleById(@PathVariable String id) {
        Optional<Module> module = moduleService.getModuleById(id);
        if (module.isPresent()) {
            return ResponseEntity.ok(module.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/api/module")
    public Module createModule(@RequestBody Module module) {
    	if(moduleService.existsByNomOrCode(module.getNom(), module.getCode())) {
    		return null;
    	}
        return moduleService.createModule(module);
    }

    @PutMapping("/api/module/{id}")
    public ResponseEntity<Module> updateModule(@PathVariable String id, @RequestBody Module moduleDetails) {
    	Optional<Module> modulesearch=moduleService.findByNom(moduleDetails.getNom());
    	System.out.println("################## "+modulesearch.isPresent());
    	if(modulesearch.isPresent()) {
    		if(!((modulesearch.get().getId()).equals(id))) {
    			return null;
    		}
    	}
    	
    	 modulesearch=moduleService.findByCode(moduleDetails.getCode());
    	if(modulesearch.isPresent()) {
    		if(!((modulesearch.get().getId()).equals(id))) {
    			return null;
    		}
    	}
    	
    	Module updatedModule = moduleService.updateModule(id, moduleDetails);
    	
        return ResponseEntity.ok(updatedModule);
    }

    @DeleteMapping("/api/module/{id}")
    public ResponseEntity<Void> deleteModule(@PathVariable String id) {
        moduleService.deleteModule(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/api/module/feature")
    public List<ModuleDTO> getAllModuleFeatures() {
        return moduleService.getAllModuleFeatures();
    }
    
    @GetMapping("/api/module/feature/{profilid}")
    public List<ModuleDTO> getAllModuleFeatures(@PathVariable String profilid) {
    	System.out.println("##################################### moduleid "+profilid);
        return moduleService.getAllModuleFeatures(profilid);
    }
    
    @GetMapping("/api/module/feature/menu")
    public ResponseEntity<String> getAllModuleFeatureMenus() {
        return ResponseEntity.ok(moduleService.getAllModuleFeatureMenus());
    }
    
    
    
    
    @GetMapping("/api/menu-items")
    public ResponseEntity<String>  getMenuItems() {
        // Example data
        MenuItem dashboard1 = new MenuItem();
        dashboard1.setPath("/dashboard/dashboard1");
        dashboard1.setTitle("Dashboard 1");
        dashboard1.setIcon("ft-arrow-right submenu-icon");
        dashboard1.setClassName("dropdown-item");
        dashboard1.setExternalLink(false);
        dashboard1.setSubmenu(Arrays.asList());

        MenuItem dashboard2 = new MenuItem();
        dashboard2.setPath("/dashboard/dashboard2");
        dashboard2.setTitle("Dashboard 2");
        dashboard2.setIcon("ft-arrow-right submenu-icon");
        dashboard2.setClassName("dropdown-item");
        dashboard2.setExternalLink(false);
        dashboard2.setSubmenu(Arrays.asList());

        MenuItem dashboard = new MenuItem();
        dashboard.setPath("");
        dashboard.setTitle("Dashboard");
        dashboard.setIcon("ft-home");
        dashboard.setClassName("dropdown nav-item has-sub");
        dashboard.setExternalLink(false);
        dashboard.setSubmenu(Arrays.asList(dashboard1, dashboard2));

        StringBuilder sb = new StringBuilder();
        sb.append("export const ROUTES: RouteInfo[] = [\n");
        for (MenuItem menuItem : Arrays.asList(dashboard)) {
            sb.append(menuItemToString(menuItem));
        }
        sb.append("];\n");
        return ResponseEntity.ok( sb.toString());
        
    }
    
    
    private String menuItemToString(MenuItem menuItem) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("  path: '").append(menuItem.getPath()).append("',\n");
        sb.append("  title: '").append(menuItem.getTitle()).append("',\n");
        sb.append("  icon: '").append(menuItem.getIcon()).append("',\n");
        sb.append("  class: '").append(menuItem.getClassName()).append("',\n");
        sb.append("  badge: '").append(menuItem.getBadge()).append("',\n");
        sb.append("  badgeClass: '").append(menuItem.getBadgeClass()).append("',\n");
        sb.append("  isExternalLink: ").append(menuItem.isExternalLink()).append(",\n");
        sb.append("  submenu: ").append(menuItemsToString(menuItem.getSubmenu()));
        sb.append("},\n");
        return sb.toString();
    }

    private String menuItemsToString(List<MenuItem> menuItems) {
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        if (menuItems != null) {
            for (MenuItem subItem : menuItems) {
                sb.append(menuItemToString(subItem));
            }
        }
        sb.append("]\n");
       // System.out.println("################################### sb.toString() "+sb.toString());
        return sb.toString();
    }
}
