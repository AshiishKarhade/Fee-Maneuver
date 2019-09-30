package com.example.fees2;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;

public class CompleteProfileActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    private FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_profile);

        final String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("student");
        //final String userID = mUser.getUid();

        final EditText comp_name = findViewById(R.id.comp_name);
        final EditText comp_year = findViewById(R.id.comp_year);
        final EditText comp_caste = findViewById(R.id.comp_caste);
        Button comp_button = findViewById(R.id.comp_button);

        comp_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = comp_name.getText().toString();
                String year = comp_year.getText().toString();
                String caste = comp_caste.getText().toString().toLowerCase();
                int totalFee = getTotalFee(caste);
                int remFee = totalFee;

                Student user = new Student(name, year, caste, totalFee, remFee);
                myRef.child(userID).setValue(user);

                updateView();
            }
        });

    }

    private int getTotalFee(String castee) {
        int fee = 0;
        if(castee=="general" || castee=="gen"){
            fee = 100000;
        }
        else if(castee=="oms"){
            fee = 70000;
        }
        else if(castee=="sc"){
            fee = 11500;
        }
        else if(castee=="st"){
            fee = 10000;
        }
        else{
            fee=0;
        }
        return fee;
    }

    public void updateView(){
        Intent nIntent = new Intent(CompleteProfileActivity.this, StudentActivity.class);
        startActivity(nIntent);
    }


}
