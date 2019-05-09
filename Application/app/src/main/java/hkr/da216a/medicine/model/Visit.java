package hkr.da216a.medicine.model;

import java.sql.Date;

public class Visit {

    private int id;
    private int idDoctor;
    private int idPatient;
    private String date;
    private String note;

    public Visit(int id, int idDoctor, int idPatient, String date, String note) {
        this.id = id;
        this.idDoctor = idDoctor;
        this.idPatient = idPatient;
        this.date = date;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(int idDoctor) {
        this.idDoctor = idDoctor;
    }

    public int getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(int idPatient) {
        this.idPatient = idPatient;
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
                "id=" + id +
                ", idDoctor=" + idDoctor +
                ", idPatient=" + idPatient +
                ", date='" + date + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
