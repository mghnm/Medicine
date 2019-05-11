package hkr.da216a.medicine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import hkr.da216a.medicine.database.DatabaseFacade;
import hkr.da216a.medicine.login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DatabaseFacade.initializeServerConnection();

        Intent myIntent = null;

        //todo check connection first

        if (CurrentDoctorSingleton.getInstance().isConnected()) {
            myIntent = new Intent(this, ActivityMainMenu.class);
        } else {
            myIntent = new Intent(this, LoginActivity.class);
        }
        startActivity(myIntent);
    }
}
