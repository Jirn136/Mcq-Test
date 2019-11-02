package com.example.mcq;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private EditText edtEmail, edtPassword, edtUsername;
    private Button btnSign_Up, btnSign_In;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        edtUsername = findViewById(R.id.edtUsername);
        btnSign_Up = findViewById(R.id.btnSign_Up);
        btnSign_In = findViewById(R.id.btnSign_In);
        progressBar = findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();

        btnSign_Up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((edtEmail.getText().toString()).equals("") || (edtPassword.getText().toString()).equals("") || (edtUsername.getText().toString()).equals("")) {
                    Toast.makeText(MainActivity.this, "Please fill the details above..", Toast.LENGTH_SHORT).show();
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    signUp();

                }
            }
        });

        btnSign_In.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((edtEmail.getText().toString()).equals("") || (edtPassword.getText().toString()).equals("") || (edtUsername.getText().toString()).equals("")) {
                    Toast.makeText(MainActivity.this, "Fill the Details above", Toast.LENGTH_SHORT).show();
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    signIn();
                }
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            transitiontoactivity();
        }
    }

    private void signUp() {
        mAuth.createUserWithEmailAndPassword(edtEmail.getText().toString(), edtPassword.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(MainActivity.this, "Sign up success", Toast.LENGTH_SHORT).show();
                            FirebaseDatabase.getInstance().getReference().child("my_users")
                                    .child(task.getResult().getUser().getUid())
                                    .child("username").setValue(edtUsername.getText().toString());
                            transitiontoactivity();
                        } else {
                            Toast.makeText(MainActivity.this, "Sign up failed", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }

                    }
                });
    }

    private void signIn() {
        mAuth.signInWithEmailAndPassword(edtEmail.getText().toString(), edtPassword.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(MainActivity.this, "Sign In Success", Toast.LENGTH_SHORT).show();
                             transitiontoactivity();

                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(MainActivity.this, "Sign In Failed", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }

    private void transitiontoactivity() {
        Intent intent = new Intent(MainActivity.this, StartGamePage.class);
           startActivity(intent);

    }
}
