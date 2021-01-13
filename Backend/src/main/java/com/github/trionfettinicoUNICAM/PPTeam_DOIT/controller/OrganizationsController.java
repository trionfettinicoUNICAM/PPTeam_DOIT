package com.github.trionfettinicoUNICAM.PPTeam_DOIT.controller;

import com.github.trionfettinicoUNICAM.PPTeam_DOIT.model.Organization;
import com.github.trionfettinicoUNICAM.PPTeam_DOIT.model.Skill;
import com.github.trionfettinicoUNICAM.PPTeam_DOIT.model.User;
import com.github.trionfettinicoUNICAM.PPTeam_DOIT.service.OrganizationsManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/organizations")
public class OrganizationsController {

    @Autowired
    private OrganizationsManager organizationsManager;

    @PreAuthorize("permitAll")
    @GetMapping("/{organizationName}")
    public Organization getOrganization(@PathVariable String organizationName){
        return organizationsManager.getOrganizationInstance(organizationName);
    }

    @PreAuthorize("permitAll")
    @GetMapping("/list/{page}")
    public Page<Organization> getPage(@PathVariable int page){
        return organizationsManager.getPage(page, 10);
    }

    @PreAuthorize("permitAll")
    @PostMapping("/createNew")
    public Organization createOrganization(@RequestBody Organization organization){
        return organizationsManager.createNewOrganization(organization);
    }

    @PreAuthorize("permitAll")
    @DeleteMapping("/{organizationName}")
    public boolean deleteOrganization(@PathVariable String organizationName){
        return organizationsManager.deleteOrganization(organizationName);
    }

    @PreAuthorize("permitAll")
    @GetMapping("/byUser/{userMail}")
    public List<Organization> getByUser(@PathVariable String userMail){
        return organizationsManager.findByUser(userMail);
    }

    @PreAuthorize("permitAll")
    @GetMapping("/getUsers/{organizationName}")
    public List<User> getUsers(@PathVariable String organizationName){
        return organizationsManager.getUsers(organizationName);
    }

    @PreAuthorize("permitAll")
    @PostMapping("/addCollaborator/{organizationName}/{userMail}")
    public boolean addCollaborator(@PathVariable String organizationName, @PathVariable String userMail, @RequestBody Skill skill){
        return organizationsManager.addCollaborator(organizationName, userMail, skill);
    }

    @PreAuthorize("permitAll")
    @GetMapping("/listCreatorOrg/{userMail}")
    public List<Organization> listOfOrganizationOfUser(@PathVariable String userMail){
        return organizationsManager.findCreator(userMail);
    }

    @PreAuthorize("permitAll")
    @PostMapping("/exist/")
    public boolean existsUser(@RequestBody String name){
        return organizationsManager.exists(name);
    }


}
