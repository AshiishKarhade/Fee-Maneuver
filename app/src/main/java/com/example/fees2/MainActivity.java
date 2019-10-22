package com.example.fees2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();

    Context mcontext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // private FirebaseAuth mAuth;
        Button b1=findViewById(R.id.bt1);
        TextView tv=findViewById(R.id.tv1);
        mcontext = MainActivity.this;

        b1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(mcontext, LoginActivity.class);
            startActivity(intent);
            }
        });


        tv.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent3=new Intent(mcontext, RegisterActivity.class);
            startActivity(intent3);
            }
        });


    }

}
