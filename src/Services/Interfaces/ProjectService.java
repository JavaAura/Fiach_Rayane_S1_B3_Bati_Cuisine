package Services.Interfaces;

import Models.Project;
import java.util.List;

public interface ProjectService {
    void addProject(Project project);
    void updateProject(Project project);
    List<Project> getAllProjects();
}
