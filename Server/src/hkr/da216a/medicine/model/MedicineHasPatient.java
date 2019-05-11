package hkr.da216a.medicine.model;

import java.io.Serializable;

public class MedicineHasPatient implements Serializable {
    public final static String TABLE_NAME = "medicine_has_patient";
    public final static String FK_MEDICINE_COLUMN = "medicine_name";
    public final static String FK_PATIENT_COLUM = "patient_personal_number";

    private String fkMedicine;
    private String fkPatient;

    public MedicineHasPatient() {
    }

    public MedicineHasPatient(String fkMedicine, String fkPatient) {
        this.fkMedicine = fkMedicine;
        this.fkPatient = fkPatient;
    }

    public String getFkMedicine() {
        return fkMedicine;
    }

    public void setFkMedicine(String fkMedicine) {
        this.fkMedicine = fkMedicine;
    }

    public String getFkPatient() {
        return fkPatient;
    }

    public void setFkPatient(String fkPatient) {
        this.fkPatient = fkPatient;
    }

    @Override
    public String toString() {
        return "MedicineHasPatient{" +
                "fkMedicine='" + fkMedicine + '\'' +
                ", fkPatient='" + fkPatient + '\'' +
                '}';
    }
}
