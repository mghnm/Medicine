package hkr.da216a.medicine.model;

import java.io.Serializable;

public class DiseaseHasPatient implements Serializable {
    public final static String TABLE_NAME = "disease_has_patient";
    public final static String FK_DISEASE_COLUMN = "disease_name";
    public final static String FK_PATIENT_COLUM = "patient_personal_number";

    private String fkDisease;
    private String fkPatient;

    public DiseaseHasPatient() {
    }

    public DiseaseHasPatient(String fkDisease, String fkPatient) {
        this.fkDisease = fkDisease;
        this.fkPatient = fkPatient;
    }

    public String getFkDisease() {
        return fkDisease;
    }

    public void setFkDisease(String fkDisease) {
        this.fkDisease = fkDisease;
    }

    public String getFkPatient() {
        return fkPatient;
    }

    public void setFkPatient(String fkPatient) {
        this.fkPatient = fkPatient;
    }

    @Override
    public String toString() {
        return "DiseaseHasPatient{" +
                "fkDisease='" + fkDisease + '\'' +
                ", fkPatient='" + fkPatient + '\'' +
                '}';
    }
}
