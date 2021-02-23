package com.github.trionfettinicoUNICAM.PPTeam_DOIT.service;

import com.github.trionfettinicoUNICAM.PPTeam_DOIT.exception.EntityNotFoundException;
import com.github.trionfettinicoUNICAM.PPTeam_DOIT.exception.IdConflictException;
import com.github.trionfettinicoUNICAM.PPTeam_DOIT.model.*;
import org.springframework.data.domain.Page;

import java.util.List;


public interface UsersManager extends EntityManager<UserEntity, String> {
    UserEntity getInstance(String userMail) throws EntityNotFoundException;

    UserEntity create(UserEntity user) throws IdConflictException;

    boolean delete(String mail) throws EntityNotFoundException;

    UserEntity update(UserEntity user) throws EntityNotFoundException;

    boolean exists(String userMail);

    boolean existSkill(Skill skill, String userMail) throws EntityNotFoundException;

    boolean hasSkillExpertFor(Skill skill, String userMail, String organizationId) throws EntityNotFoundException;

    List<UserSubmissionInformation> getUserSubmissions(String userMail) throws EntityNotFoundException;

    Page<UserEntity> getPage(int page, int size) throws EntityNotFoundException;

    Page<UserEntity> getExpertPage(int page, int size) throws EntityNotFoundException;

    boolean addNewSkill(String skillName, String userMail) throws EntityNotFoundException;

    boolean removeSkill(Skill skill, String userMail) throws EntityNotFoundException;
}