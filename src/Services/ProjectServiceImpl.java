package Services;

import Models.Project;
import Repository.Interfaces.ProjectRepository;
import Repository.ProjectRepositoryImpl;
import Services.Interfaces.ProjectService;

import java.util.List;

public class ProjectServiceImpl implements ProjectService {
    private ProjectRepository projectRepository;

    public ProjectServiceImpl() {
        this.projectRepository = new ProjectRepositoryImpl();
    }

    @Override
    public void addProject(Project project) {
        projectRepository.addProject(project);
    }

    @Override
    public void updateProject(Project project) {
        projectRepository.updateProject(project);
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.getAllProjects();
    }
}
