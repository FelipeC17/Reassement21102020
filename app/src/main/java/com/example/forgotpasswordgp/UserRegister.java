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

public class UserRegister extends AppCompatActivity implements View.OnClickListener {

     private FirebaseAuth mAuth;
     private TextView rbutton;
     private EditText rfullname, remail, rage, rpassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        mAuth = FirebaseAuth.getInstance();
        rbutton = (Button) findViewById(R.id.button3);
        rfullname = (EditText) findViewById(R.id.Registername);
        remail = (EditText) findViewById(R.id.remail);
        rage = (EditText) findViewById(R.id.Registerage);
        rpassword = (EditText) findViewById(R.id.rpassword);


        rbutton.setOnClickListener(this);

// rbutton.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //  public void onClick(View view) {
        //    register();

        //  }
        //});

        }

    @Override
    public void onClick(View view) {
        registerUser();
    }

    private void registerUser() {
         String fullname = rfullname.getText().toString().trim();
         String age = rage.getText().toString().trim();
         String email = remail.getText().toString().trim();
         String password = rpassword.getText().toString().trim();

        if (fullname.isEmpty()) {
            rfullname.setError("Name is Required");
            return;
        }

        if (age.isEmpty()) {
            rage.setError("Age is required");
            return;
        }

        if (email.isEmpty()) {
            remail.setError("Email is required");
            return;
        }

        if (password.isEmpty()) {
            rpassword.setError("Password is required");
            return;
        }

        if (password.length() < 5) {
            rpassword.setError("Password has to be more than 5 characters");
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful() ) {
                    User user = new User(fullname,age,email);

                    FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()) {
                                Toast.makeText(UserRegister.this, "User Created", Toast.LENGTH_LONG).show();
                                //redirect to loging
                            } else{
                                Toast.makeText(UserRegister.this, "User Not Created", Toast.LENGTH_LONG).show();
                            }

                        }
                    });
                } else {
                    Toast.makeText(UserRegister.this, "User Not Created", Toast.LENGTH_LONG).show();
                }

            }
        });


    }





}



