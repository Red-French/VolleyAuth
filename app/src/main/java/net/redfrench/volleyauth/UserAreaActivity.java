package net.redfrench.volleyauth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class UserAreaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etAge = (EditText) findViewById(R.id.etAge);
        final TextView welcomeMsg = (TextView) findViewById(R.id.tvWelcome);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String username = intent.getStringExtra("username");
        int age = intent.getIntExtra("age", -1);  // getIntExtra requires initializing int value (-1 will indicate a value wasn't passed)

        String message = name + ", welcome to your user area.";
        welcomeMsg.setText(message);
        etUsername.setText(username);
        etAge.setText(age + "");  // can't display int in a text field directly, so add empty string to convert this to a string
    }
}
