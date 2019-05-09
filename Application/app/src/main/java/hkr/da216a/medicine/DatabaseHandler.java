package hkr.da216a.medicine;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
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

    private HttpURLConnection getUrlHttpConnection(String url, boolean doOutput, boolean doInput) throws IOException {
        URL loginUrl = new URL(url);
        HttpURLConnection httpURLConnection = (HttpURLConnection) loginUrl.openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoOutput(doOutput);
        httpURLConnection.setDoInput(doInput);
        return httpURLConnection;
    }

    @Override
    protected String doInBackground(String... params) {
        preferences = context.getSharedPreferences("MYPREFS", Context.MODE_PRIVATE);
        editor = preferences.edit();
        editor.putString("flag", "0");
        editor.commit();

        String task = params[0];
        switch (task) {
            case "login": {
                return loginQuery(params);
            }
            case "register": {
                return registerQuery(params);
            }
        }
        return null;
    }

    //This method will be called when doInBackground completes... and it will return the completion
    // string which will display this toast. s is the echo from the .php file
    @Override
    protected void onPostExecute(String result) {
        String flag = preferences.getString("flag", "0");

        switch (flag) {
            case "login": {
                loginResponse(result);
                break;
            }
            case "register": {
                registerResponse(result);
                break;
            }
            default: {
                display("Login Failed...", "Something weird happened :(.");
                break;
            }
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

    private void setFlag(String flag) {
        editor.putString("flag", flag);
        editor.commit();
    }

    private String registerQuery(String[] params) {
        setFlag("register");

        String registerEmail = params[1];
        String registerPassword = params[2];
        String repeatRegisterPassword = params[2];

        try {
            HttpURLConnection httpURLConnection = getUrlHttpConnection(urlRegistration, true, false);
            try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(httpURLConnection.getOutputStream(), StandardCharsets.UTF_8))) {
                String myData =
                        URLEncoder.encode("identifier_email", "UTF-8") + "=" + URLEncoder.encode(registerEmail, "UTF-8") + "&"
                                + URLEncoder.encode("identifier_password", "UTF-8") + "=" + URLEncoder.encode(registerPassword, "UTF-8") + "&"
                                + URLEncoder.encode("identifier_repeat_password", "UTF-8") + "=" + URLEncoder.encode(repeatRegisterPassword, "UTF-8");
                bufferedWriter.write(myData);
                bufferedWriter.flush();
            }
            return "Successfully Registered " + registerEmail;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void registerResponse(String result) {
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }

    private String loginQuery(String... params) {
        setFlag("login");

        String loginEmail = params[1];
        String loginPassword = params[2];
        try {
            HttpURLConnection httpURLConnection = getUrlHttpConnection(urlLogin, true, true);

            //send the email and password to the database
            try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(httpURLConnection.getOutputStream(), StandardCharsets.UTF_8))) {
                String myData =
                        URLEncoder.encode("identifier_login_email", "UTF-8") + "=" + URLEncoder.encode(loginEmail, "UTF-8") + "&"
                                + URLEncoder.encode("identifier_login_password", "UTF-8") + "=" + URLEncoder.encode(loginPassword, "UTF-8");
                bufferedWriter.write(myData);
                bufferedWriter.flush();
            }

            //get response from the database
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), StandardCharsets.UTF_8))) {
                String dataResponse = "";
                String inputLine = "";
                while ((inputLine = bufferedReader.readLine()) != null) {
                    dataResponse += inputLine;
                }
                httpURLConnection.disconnect();

                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                System.out.println(dataResponse);
                return dataResponse;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void loginResponse(String result) {
        String[] serverResponse = result.split("[,]");
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
    }
}
