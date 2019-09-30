package com.example.fees2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class TeacherActivity extends AppCompatActivity {

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        Button updateFees = findViewById(R.id.teacher_updatebtn);
        Button signout  = findViewById(R.id.teacher_signout);


        updateFees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent updateIntent = new Intent(TeacherActivity.this, UpdateFeesActivity.class);
                startActivity(updateIntent);
            }
        });




        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.getInstance().signOut();

                startActivity(new Intent(TeacherActivity.this, LoginActivity.class));
                finish();
            }
        });

    }
}
