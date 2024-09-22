package Repository.Interfaces;

import Models.Material;

import java.util.List;

public interface MaterialRepository {
    void addMaterial(Material material);
    List<Material> getAllMaterials();
}
