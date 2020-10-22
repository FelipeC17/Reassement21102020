package com.example.forgotpasswordgp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class UserRegister extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button rbutton;
    private EditText rfullname, remail, rage, rpassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        mAuth = FirebaseAuth.getInstance();
        rfullname = (EditText) findViewById(R.id.Registername);
        remail = (EditText) findViewById(R.id.remail);
        rage = (EditText) findViewById(R.id.Registerage);
        rpassword = (EditText) findViewById(R.id.rpassword);

        rbutton = (Button) findViewById(R.id.button3);

        rbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

    }

    private void registerUser() {
        String username = rfullname.getText().toString().trim();
        String userage = rage.getText().toString().trim();
        String useremail = remail.getText().toString().trim();
        String userpassword = rpassword.getText().toString().trim();

        if (username.isEmpty()) {
            rfullname.setError("Name is Required");
            return;
        }

        if (userage.isEmpty()) {
            rage.setError("Age is required");
            return;
        }

        if (useremail.isEmpty()) {
            remail.setError("Email is required");
            return;
        }

        if (userpassword.isEmpty()) {
            remail.setError("Password is required");
            return;
        }

        if (userpassword.length() < 5) {
            rpassword.setError("Password has to be more than 5 characters");
            return;
        }

    }
}