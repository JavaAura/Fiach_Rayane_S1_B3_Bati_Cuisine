package Repository.Interfaces;

import Models.Project;
import java.util.List;

public interface ProjectRepository {
    void addProject(Project project);
    List<Project> getAllProjects();
    Project getProjectById(int id);
}
