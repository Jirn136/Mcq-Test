package com.example.mcq;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Quiz extends AppCompatActivity implements View.OnClickListener {

    private TextView txtQues, txtIndex;
    private Button btnNext;
    private CheckBox checkBox1, checkBox2, checkBox3, checkBox4;
    private Questions questions=new Questions();
    private int i = 0;
    private int Right = 0, Wrong = 0;
    private String mAns;
    private boolean isAnswered = true;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mAuth = FirebaseAuth.getInstance();

        txtQues = findViewById(R.id.txtQues);
        txtIndex = findViewById(R.id.txtIndex);
        btnNext = findViewById(R.id.btnNext);

        checkBox1 = findViewById(R.id.custom_checkBox1);
        checkBox2 = findViewById(R.id.custom_checkBox2);
        checkBox3 = findViewById(R.id.custom_checkBox3);
        checkBox4 = findViewById(R.id.custom_checkBox4);

        upDatequestions(i++);

        checkBox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox1.isChecked()) {
                    checkBox1.setButtonDrawable(getResources().getDrawable(R.drawable.custom_checkbox));
                    checkBox2.setChecked(false);
                    checkBox3.setChecked(false);
                    checkBox4.setChecked(false);

                } else if (checkBox1.isChecked()) {
                    checkBox1.setButtonDrawable(getResources().getDrawable(R.drawable.unchecked));
                }
                if (isAnswered) {
                    if (checkBox1.getText().equals(mAns)) {
                        Right++;
                    } else {
                        Wrong++;
                    }
                    isAnswered = !isAnswered;
                }
            }
        });

        checkBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox2.isChecked()) {
                    checkBox2.setButtonDrawable(getResources().getDrawable(R.drawable.custom_checkbox));
                    checkBox1.setChecked(false);
                    checkBox3.setChecked(false);
                    checkBox4.setChecked(false);

                } else if (checkBox2.isChecked()) {
                    checkBox2.setButtonDrawable(getResources().getDrawable(R.drawable.unchecked));

                }
                if (isAnswered) {
                    if (checkBox2.getText().equals(mAns)) {
                        Right++;
                    } else {
                        Wrong++;
                        isAnswered = !isAnswered;
                    }
                }


            }
        });

        checkBox3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox3.isChecked()) {
                    checkBox3.setButtonDrawable(getResources().getDrawable(R.drawable.custom_checkbox));
                    checkBox1.setChecked(false);
                    checkBox2.setChecked(false);
                    checkBox4.setChecked(false);
                } else if (checkBox1.isChecked()) {
                    checkBox3.setButtonDrawable(getResources().getDrawable(R.drawable.unchecked));

                }
                if (isAnswered) {
                    if (checkBox3.getText().equals(mAns)) {
                        Right++;
                    } else {
                        Wrong++;
                    }
                    isAnswered = !isAnswered;
                }

            }
        });

        checkBox4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox4.isChecked()) {
                    checkBox4.setButtonDrawable(getResources().getDrawable(R.drawable.custom_checkbox));
                    checkBox1.setChecked(false);
                    checkBox2.setChecked(false);
                    checkBox3.setChecked(false);
                } else if (checkBox4.isChecked()) {
                    checkBox1.setButtonDrawable(getResources().getDrawable(R.drawable.unchecked));

                }
                if (isAnswered) {
                    if (checkBox4.getText().equals(mAns)) {
                        Right++;
                    } else {
                        Wrong++;
                    }
                    isAnswered = !isAnswered;
                }


            }
        });
        btnNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (i <= 9) {
            if ((!checkBox1.isChecked()) && (!checkBox2.isChecked()) && (!checkBox3.isChecked()) && (!checkBox4.isChecked())) {
                Toast.makeText(this, "Please Select One..", Toast.LENGTH_SHORT).show();
            } else {
                upDatequestions(i++);
                txtIndex.setText(i + "/10");
                isAnswered = true;
            }

        } else {
            btnNext.setText("Finish");
            Toast.makeText(this, "You've Completed the Test", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, Final_showdown.class);
            intent.putExtra("RightAns", Right);
            intent.putExtra("WrongAns", Wrong);
            startActivity(intent);
        }

    }

    public void upDatequestions(int num) {
        txtQues.setText(questions.getQuestion(num));
        checkBox1.setText(questions.getChoice1(num));
        checkBox1.setChecked(false);
        checkBox2.setText(questions.getChoice2(num));
        checkBox2.setChecked(false);
        checkBox3.setText(questions.getChoice3(num));
        checkBox3.setChecked(false);
        checkBox4.setText(questions.getChoice4(num));
        checkBox4.setChecked(false);

        mAns = questions.getCorrectAnswer(num);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.LogoutItem:
                logout();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(false);
         Toast.makeText(this, "You are unable to go back!!! ", Toast.LENGTH_SHORT).show();

    }
    private void logout() {
        mAuth.signOut();
        finish();
        Intent intent0=new Intent(this,MainActivity.class);
        startActivity(intent0);
    }

}

