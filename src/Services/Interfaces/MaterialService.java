package Services.Interfaces;

import Models.Material;

import java.util.List;

public interface MaterialService {
    void addMaterial(Material material);
    List<Material> getAllMaterials();
}
