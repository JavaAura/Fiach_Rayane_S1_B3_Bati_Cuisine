package Services;

import Models.Enum.Status;
import Models.Project;
import Repository.Interfaces.ProjectRepository;
import Repository.ProjectRepositoryImpl;
import Services.Interfaces.ProjectService;

import java.util.List;
import java.util.stream.Collectors;

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
    public Project getProjectByName(String projectName){
        return projectRepository.getProjectByName(projectName);
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.getAllProjects();
    }

    @Override
    public List<Project> getProjectByStatus(){
        return getAllProjects().stream().filter(p -> p.getProjectStatus() == Status.DONE).collect(Collectors.toList());
    }

}
