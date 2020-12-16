package com.github.trionfettinicoUNICAM.PPTeam_DOIT.service;

import com.github.trionfettinicoUNICAM.PPTeam_DOIT.model.Organization;
import com.github.trionfettinicoUNICAM.PPTeam_DOIT.model.Project;
import com.github.trionfettinicoUNICAM.PPTeam_DOIT.repository.OrganizationRepository;
import com.github.trionfettinicoUNICAM.PPTeam_DOIT.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SimpleProjectManager implements ProjectsManager{
    //TODO migliorare i controlli e le condizioni sui metodi

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private OrganizationRepository organizationRepository;

    @Override
    public Project getProjectInstance(String projectID) {
        return projectRepository.findById(projectID).orElse(null);
    }

    @Override
    public Project createNewProject(Project project) {
        Optional<Organization> organization = organizationRepository.findById(project.getOrganizationName());
        if (organization.isPresent() && organization.get().getMembersMails().contains(project.getCreatorMail()))
            return projectRepository.save(project);
        else return null;
    }

    @Override
    public boolean closeProject(String projectID) {
        Project toClose = getProjectInstance(projectID);
        toClose.close();
        projectRepository.save(toClose);
        return getProjectInstance(projectID).isClosed();
    }

    @Override
    public boolean deleteProject(String projectID) {
        projectRepository.deleteById(projectID);
        return !exists(projectID);
    }

    @Override
    public boolean updateProject(Project project) {
        projectRepository.save(project);
        return getProjectInstance(project.getName()).equals(project);
    }

    @Override
    public Page<Project> getPage(int page, int size) {
        return projectRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public boolean exists(String projectID) {
        return projectRepository.existsById(projectID);
    }

    @Override
    public List<Project> findByOrganizationName(String organizationName) {
        return projectRepository.findByOrganizationName(organizationName);
    }
}