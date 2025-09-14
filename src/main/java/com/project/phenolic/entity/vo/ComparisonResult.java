package com.project.phenolic.entity.vo;

import com.project.phenolic.entity.MedicinalPlants;
import com.project.phenolic.entity.UnknownPlants;
import lombok.Data;

@Data
public class ComparisonResult {

    private UnknownPlants unknownPlants;

    private MedicinalPlants medicinalPlants;

    public ComparisonResult(UnknownPlants unknownPlants, MedicinalPlants medicinalPlants) {
        this.unknownPlants = unknownPlants;
        this.medicinalPlants = medicinalPlants;
    }
    public ComparisonResult() {
    }
}
