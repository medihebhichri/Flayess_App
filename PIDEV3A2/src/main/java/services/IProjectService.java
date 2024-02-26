package services;

import models.Project;

import java.sql.Connection;

public interface IProjectService {
    void addProject(String projectName, Project projectDetails);
    Project getProject(String projectName);
    void updateProjectDetails(String projectName, Project updatedDetails);
    void deleteProject(String projectName);
}
