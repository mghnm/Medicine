package hkr.da216a.medicine;

import hkr.da216a.medicine.model.Doctor;

public class CurrentDoctorSingleton {

    private Doctor doctor;
    private boolean connected = false;

    private static CurrentDoctorSingleton ourInstance;

    public static CurrentDoctorSingleton getInstance() {
        if (ourInstance == null) {
            ourInstance = new CurrentDoctorSingleton();
        }
        return ourInstance;
    }

    private CurrentDoctorSingleton() {
    }

    public static CurrentDoctorSingleton getOurInstance() {
        return ourInstance;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }
}
