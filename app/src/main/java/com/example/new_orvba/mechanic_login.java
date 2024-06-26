package com.example.new_orvba;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class mechanic_login extends AppCompatActivity {
    private Button loginButton;
    private EditText emailEditText;
    private EditText passwordEditText;
    private TextView Tv;
    private FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
        // Removed the auto-redirection logic from onStart
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mechanic_login);

        mAuth = FirebaseAuth.getInstance();

        loginButton = findViewById(R.id.button);
        emailEditText = findViewById(R.id.Username);
        passwordEditText = findViewById(R.id.Password);
        Tv = findViewById(R.id.newuser);

        Tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add your logic for handling the click on the TextView (Tv)
                // For example, navigate to the user registration page
                Intent intent = new Intent(mechanic_login.this, mechanic_form.class);
                startActivity(intent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = String.valueOf(emailEditText.getText());
                String password = String.valueOf(passwordEditText.getText());

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(mechanic_login.this, "Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(mechanic_login.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(mechanic_login.this, "Login Successfully.", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), home_page.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(mechanic_login.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}
