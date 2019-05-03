package hkr.da216a.medicine;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class DatabaseHandler extends AsyncTask<String, Void, String> {

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private Context context;

    private String urlRegistration = "http://da216a.gearhostpreview.com/register.php";
    private String urlLogin = "http://da216a.gearhostpreview.com/login.php";

    public DatabaseHandler(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
        preferences = context.getSharedPreferences("MYPREFS", Context.MODE_PRIVATE);
        editor = preferences.edit();
        editor.putString("flag", "0");
        editor.commit();

        String task = params[0];

        if (task.equals("login")) {
            String loginEmail = params[1];
            System.out.println("De-de-debug! " + loginEmail);
            String loginPassword = params[2];
            System.out.println("De-de-debug! " + loginPassword);
            try {
                URL loginUrl = new URL(urlLogin);
                HttpURLConnection httpURLConnection = (HttpURLConnection) loginUrl.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                //send the email and password to the database
                try (OutputStream outputStream = httpURLConnection.getOutputStream()) {
                    try (OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)) {
                        try (BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter)) {
                            String myData =
                                    URLEncoder.encode("identifier_login_email", "UTF-8") + "=" + URLEncoder.encode(loginEmail, "UTF-8") + "&"
                                            + URLEncoder.encode("identifier_login_password", "UTF-8") + "=" + URLEncoder.encode(loginPassword, "UTF-8");
                            bufferedWriter.write(myData);
                            bufferedWriter.flush();
                        }
                    }
                }

                //get response from the database
                try (InputStream inputStream = httpURLConnection.getInputStream()) {
                    try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
                        try (BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
                            String dataResponse = "";
                            String inputLine = "";
                            while ((inputLine = bufferedReader.readLine()) != null) {
                                dataResponse += inputLine;
                            }
                            httpURLConnection.disconnect();

                            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                            System.out.println(dataResponse);

                            editor.putString("flag", "login");
                            editor.commit();
                            return dataResponse;
                        }
                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (task.equals("register")) {
            String registerEmail = params[1];
            String registerPassword = params[2];
            String repeatRegisterPassword = params[2];

            try {
                URL registerUrl = new URL(urlRegistration);
                HttpURLConnection httpURLConnection = (HttpURLConnection) registerUrl.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                try (OutputStream outputStream = httpURLConnection.getOutputStream()) {
                    try (OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)) {
                        try (BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter)) {
                            String myData =
                                    URLEncoder.encode("identifier_email", "UTF-8") + "=" + URLEncoder.encode(registerEmail, "UTF-8") + "&"
                                            + URLEncoder.encode("identifier_password", "UTF-8") + "=" + URLEncoder.encode(registerPassword, "UTF-8") + "&"
                                            + URLEncoder.encode("identifier_repeat_password", "UTF-8") + "=" + URLEncoder.encode(repeatRegisterPassword, "UTF-8");
                            bufferedWriter.write(myData);
                            bufferedWriter.flush();
                        }
                    }
                }
                try (InputStream inputStream = httpURLConnection.getInputStream()) {
                }

                editor.putString("flag", "register");
                editor.commit();
                return "Successfully Registered " + registerEmail;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    //This method will be called when doInBackground completes... and it will return the completion
    // string which will display this toast. s is the echo from the .php file
    @Override
    protected void onPostExecute(String s) {
        String flag = preferences.getString("flag", "0");

        if (flag.equals("register")) {
            Toast.makeText(context, s, Toast.LENGTH_LONG).show();
        }
        if (flag.equals("login")) {
            String[] serverResponse = s.split("[,]");
            String test = serverResponse[0];
            String name = serverResponse[1];
            String email = serverResponse[2];

            if (test.equals("true")) {
                editor.putString("name", name);
                editor.commit();
                editor.putString("last_name", email);
                editor.commit();
                //Intent intent = new Intent(context, MainActivity.class);//TODO successful login handling
                display("Login success", "Logged in with: " + email);
                //context.startActivity(intent);
            } else {
                display("Login Failed...", "That email and password do not match our records :(.");
            }
        } else {
            display("Login Failed...", "Something weird happened :(.");
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    private void display(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
