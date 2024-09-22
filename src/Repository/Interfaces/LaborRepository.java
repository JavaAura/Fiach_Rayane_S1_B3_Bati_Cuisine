package Repository.Interfaces;

import Models.Labor;

import java.util.List;

public interface LaborRepository {
    void addLabor(Labor labor);
    List<Labor> getAllLabors();
}
