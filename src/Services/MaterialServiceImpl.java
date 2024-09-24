package Services;

import Models.Material;
import Repository.MaterialRepositoryImpl;
import Services.Interfaces.MaterialService;
import Repository.Interfaces.MaterialRepository;


import java.util.List;

public class MaterialServiceImpl implements MaterialService {

    private MaterialRepository materialRepository;

    public MaterialServiceImpl() {
        this.materialRepository = new MaterialRepositoryImpl();
    }

    @Override
    public void addMaterial(Material material) {
        materialRepository.addMaterial(material);
    }

    @Override
    public List<Material> getAllMaterials() {
        return materialRepository.getAllMaterials();
    }

    @Override
    public List <Material> getMaterialById(int projectId) {
        return materialRepository.getMaterialById(projectId);
    }

    public double[] calculateMaterialCosts(List<Material> materials) {
        double totalCostBeforeVAT = 0.0;
        double totalCostWithVAT = 0.0;

        for (Material material : materials) {
            double totalCost = material.getTotalCost();
            totalCostBeforeVAT += totalCost;
            double vatCost = totalCost * (material.getVatRate() / 100);
            totalCostWithVAT += totalCost + vatCost;
        }

        return new double[]{totalCostBeforeVAT, totalCostWithVAT};
    }
}
