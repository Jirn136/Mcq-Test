package com.example.mcq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Final_showdown extends AppCompatActivity {

    private TextView txtRight,txtWrong,txtScore,txtperCentage;
    private Button btnOK;
    private int percentage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_showdown);

        txtRight=findViewById(R.id.txtRight);
        txtWrong=findViewById(R.id.txtWrong);
        txtScore=findViewById(R.id.txtScore);
        txtperCentage=findViewById(R.id.txtperCentage);
        btnOK=findViewById(R.id.btnOK);

        setTitle("Final Result");

        Intent intent =getIntent();
        final int Right=intent.getIntExtra("RightAns",0);
        int Wrong=intent.getIntExtra("WrongAns",0);

        txtRight.setText(String.valueOf(Right));
        txtWrong.setText(String.valueOf(Wrong));

        txtScore.setText(String.valueOf(Right));

        percentage=(Right*100)/10;
        txtperCentage.setText(percentage + "%");

        FirebaseDatabase.getInstance().getReference().child("my_users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Right").setValue(Right);
        FirebaseDatabase.getInstance().getReference().child("my_users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Wrong").setValue(Wrong);


        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Final_showdown.this,StartGamePage.class);
                startActivity(intent);
            }
        });

    }
}

