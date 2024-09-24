package Services;

import Models.Labor;
import Repository.LaborRepositoryImpl;
import Services.Interfaces.LaborService;
import Repository.Interfaces.LaborRepository;

import java.util.List;

public class LaborServiceImpl implements LaborService {
    private LaborRepository laborRepository;

    public LaborServiceImpl() {
        this.laborRepository = new LaborRepositoryImpl();
    }

    @Override
    public void addLabor(Labor labor) {
        laborRepository.addLabor(labor);
    }

    @Override
    public List<Labor> getAllLabors() {
        return laborRepository.getAllLabors();
    }

    @Override
    public List<Labor> getLaborById(int projectId){
        return laborRepository.getLaborById(projectId);

    }

}
