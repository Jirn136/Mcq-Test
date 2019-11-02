package com.example.mcq;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class StartGamePage extends AppCompatActivity implements View.OnClickListener{

    private Button btnStartGame;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game_page);

        btnStartGame=findViewById(R.id.btnStartGame);
        mAuth=FirebaseAuth.getInstance();

        btnStartGame.setOnClickListener(this);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.btnStartGame:
                Intent intent1=new Intent(StartGamePage.this,Quiz.class);
                startActivity(intent1);

                break;

        }
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

                new AlertDialog.Builder(this).setTitle("Confirmation to logout").setMessage("Are you sure, you want to logout?")
                        .setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    logout();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).show();
        return super.onOptionsItemSelected(item);
    }

    public void logout(){
       mAuth.signOut();
        finish();
        startActivity(new Intent(this,MainActivity.class));
    }
}
