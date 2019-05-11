package hkr.da216a.servertesting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(new Runnable() {
            @Override
            public void run() {
                //Prepare connection
                DatabaseFacade.getInstance();
            }
        }).start();
        this.textView = findViewById(R.id.textView);
        this.button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //runOnUiThread way

                /*new Thread(new Runnable() {
                    @Override
                    public void run() {
                        new Client(MainActivity.this);
                    }
                }).start();*/

                //Async way

                DatabaseFacade.getInstance().loginUser(MainActivity.this, "ste", "ste");
            }
        });
    }

    public void loginUser(Doctor doctor) {
        System.out.println("Logging in with:");
        System.out.println(doctor);
    }

    public void setTextViewText(String text) {
        textView.setText(text);
    }
}
