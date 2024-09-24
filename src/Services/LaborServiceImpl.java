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

    public double[] calculateLaborCosts(List<Labor> labors) {
        double totalCostBeforeVAT = 0.0;
        double totalCostWithVAT = 0.0;

        if (labors == null || labors.isEmpty()) {
            return new double[]{totalCostBeforeVAT, totalCostWithVAT}; // Return zeros if no labors
        }

        for (Labor labor : labors) {
            double laborCost = labor.getTotalCost();
            totalCostBeforeVAT += laborCost;

            // Calculate VAT
            double laborVatCost = laborCost * (labor.getVatRate() / 100);
            totalCostWithVAT += laborCost + laborVatCost;
        }

        return new double[]{totalCostBeforeVAT, totalCostWithVAT};
    }

}
