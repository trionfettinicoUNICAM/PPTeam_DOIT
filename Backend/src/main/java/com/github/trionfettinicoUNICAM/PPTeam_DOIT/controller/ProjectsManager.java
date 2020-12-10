package com.github.trionfettinicoUNICAM.PPTeam_DOIT.controller;

import com.github.trionfettinicoUNICAM.PPTeam_DOIT.model.*;

/**
 * This interface is responsible for managing all projects of the application,
 * it knows how to get every project by it's ID and can perform basic operations on them.
 */
public interface ProjectsManager {
    /**
     * Retrieves the project associated with the given ID and returns an instance of it.
     * @param projectID the ID of the wanted project
     * @return an instance of the project
     */
    Project getProjectInstance(String projectName);

    /**
     * Creates a new empty (with an empty team and no experts) project. The new project is associated
     * with the given {@link Organization}.
     * @param organization the organization responsible for the project creation
     * @param title the title of the new project
     * @param description the text description of the new project
     * @return the newly created Project
     */
    Project openNewEmptyProject(Organization organization, String title, String description, User creator);

    /**
     * Closes the project identified by the given ID. The project is NOT deleted from the system,
     * it will only be marked as "closed" and some useless information might be deleted.
     * @param projectName the name of the project to close
     * @return true if the project has been deleted, false instead
     * @see ProjectsManager#deleteProject(String)
     */
    boolean closeProject(String projectName);

    /**
     * Deletes a project from the system.
     * @param projectID the ID of the project to delete
     * @return true if the project has been deleted, false instead
     */
    boolean deleteProject(String projectName);

    /**
     * Updates the {@link Project} passed as parameter and saves it.
     * @param project the project to be saved
     * @return true if the project is stored successfully, false instead.
     */
    boolean updateProject(String projectName);
}