package Services.Interfaces;

import Models.Labor;

import java.util.List;

public interface LaborService {
    void addLabor(Labor labor);
    List<Labor> getAllLabors();
}
