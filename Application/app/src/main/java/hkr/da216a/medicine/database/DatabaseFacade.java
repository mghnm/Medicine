package hkr.da216a.medicine.database;

import hkr.da216a.medicine.login.LoginActivity;
import hkr.da216a.medicine.model.Doctor;

public abstract class DatabaseFacade {

    /**
     * Has to be run at the beginning of the application start to establish connection
     */
    public static void initializeServerConnection() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                DatabaseHandler.getInstance().prepareConnection();
            }
        }).start();
    }

    /**
     * When called stops the connection with the server
     * To be called when the application shuts down
     * ps: not really need to call it as when the app is closed, the server drops the socket itself
     */
    public static void stopServerConnection() {
        DatabaseHandler.getInstance().finishConnection();
    }

    public static void loginWithCredentials(final LoginActivity loginActivity, final String personalNumber, final String password) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Doctor doctor = DatabaseHandler.getInstance().getDoctor(personalNumber, password);
                System.out.println("Got doctor: " + doctor);
                if (doctor.getPersonalNumber().equals(personalNumber) &&
                        doctor.getPassword().equals(password)) {
                    loginActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            loginActivity.openMainScene();
                        }
                    });
                }
            }
        }).start();
    }
}
