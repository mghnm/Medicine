package hkr.da216a.medicine.model;

import java.io.Serializable;

public class Visit implements Serializable {
    public final static String TABLE_NAME = "visit";
    public final static String ID_COLUMN = "id_visit";
    public final static String FK_DOCTOR_COLUMN = "doctor_personal_number";
    public final static String FK_PATIENT_COLUMN = "patient_personal_number";
    public final static String DATE_COLUMN = "date";
    public final static String NOTE_COLUMN = "note";

    private int idVisit;
    private int fkDoctor;
    private String fkPatient;
    private String date;
    private String note;

    public Visit() {
    }

    public Visit(int idVisit, int fkDoctor, String fkPatient, String date, String note) {
        this.idVisit = idVisit;
        this.fkDoctor = fkDoctor;
        this.fkPatient = fkPatient;
        this.date = date;
        this.note = note;
    }

    public int getIdVisit() {
        return idVisit;
    }

    public void setIdVisit(int idVisit) {
        this.idVisit = idVisit;
    }

    public int getFkDoctor() {
        return fkDoctor;
    }

    public void setFkDoctor(int fkDoctor) {
        this.fkDoctor = fkDoctor;
    }

    public String getFkPatient() {
        return fkPatient;
    }

    public void setFkPatient(String fkPatient) {
        this.fkPatient = fkPatient;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Visit{" +
                "idVisit=" + idVisit +
                ", fkDoctor=" + fkDoctor +
                ", fkPatient='" + fkPatient + '\'' +
                ", date='" + date + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
