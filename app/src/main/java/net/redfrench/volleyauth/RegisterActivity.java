package net.redfrench.volleyauth;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final EditText etAge = (EditText) findViewById(R.id.etAge);
        final Button btnRegister = (Button) findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String name = etName.getText().toString();
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();
                final int age = Integer.parseInt(etAge.getText().toString());

                final Response.Listener<String> responseListener = new Response.Listener<String>() {

                    // below happens when response has been executed
                    @Override
                    public void onResponse(String response) {  // 'response' is the boolean response from Register.php
                        try {
                            JSONObject jsonResponse = new JSONObject(response);  // gets 'response' string Volley has given back; 'response' was encoded into JSON string in Register.php
                            boolean success = jsonResponse.getBoolean("success");  // 'success' given a boolean value in Register.php
                            if(success) {
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Registration failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                RegisterRequest registerRequest = new RegisterRequest(name, username, password, age, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
//                Log.v("registerRequest", String.valueOf(registerRequest));
                queue.add(registerRequest);
            }
        });
    }
}
