package com.example.forgotpasswordgp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private TextView register;

   private EditText emailog, passwordlog;
   private Button signin;
   private FirebaseAuth mauth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mauth = FirebaseAuth.getInstance();

        emailog = (EditText) findViewById(R.id.emailL);
        passwordlog = (EditText) findViewById(R.id.passwordL);

        register = (TextView) findViewById(R.id.GoRegister);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, UserRegister.class));
            }
        });

        signin = (Button) findViewById(R.id.button);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });


    }

    private void userLogin() {
        String email = emailog.getText().toString().trim();
        String password = passwordlog.getText().toString().trim();

        if (email.isEmpty()) {
            emailog.setError("Email is required");
            return;
        }

        if (password.isEmpty()) {
            passwordlog.setError("Password is required");
            return;
        }

        mauth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful() ) {

                    startActivity(new Intent(MainActivity.this, UserProfile.class));

                }else {
                    Toast.makeText(MainActivity.this, "Failed to Login", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
}



