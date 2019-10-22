package com.example.fees2;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;

public class CompleteProfileActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    private FirebaseUser mUser;

    private int totalFees, genFee, omsFee, obcFee, scFee, stFee;
    private String caste;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference feeRef = database.getReference("fees");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_profile);

        final String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        final DatabaseReference myRef = database.getReference("student");
        //final String userID = mUser.getUid();
        final ProgressBar mpro = findViewById(R.id.comp_progressBar);
        mpro.setVisibility(View.GONE);

        final EditText comp_name = findViewById(R.id.comp_name);
        final EditText comp_year = findViewById(R.id.comp_year);
        //final EditText comp_caste = findViewById(R.id.comp_caste);
        Button comp_button = findViewById(R.id.comp_button);

        updateTheFees();

        comp_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mpro.setVisibility(View.VISIBLE);

                String name = comp_name.getText().toString();
                String year = comp_year.getText().toString();
               // String caste = comp_caste.getText().toString().toLowerCase();
               // int totalFee = getTotalFee(caste);
                int remFee = totalFees;

                if(name.isEmpty()){
                    Toast.makeText(CompleteProfileActivity.this, "Name cannot be empty", Toast.LENGTH_SHORT).show();
                    mpro.setVisibility(View.GONE);
                }
                else if(year.isEmpty()){
                    Toast.makeText(CompleteProfileActivity.this, "Year cannot be empty", Toast.LENGTH_SHORT).show();
                    mpro.setVisibility(View.GONE);
                }

                else{
                    Student user = new Student(name, year, caste, totalFees, remFee);
                    myRef.child(userID).setValue(user);

                    mpro.setVisibility(View.GONE);
                    updateView();
                }

            }
        });

    }

    private void updateTheFees() {
        feeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                genFee = Integer.parseInt(dataSnapshot.child("general").getValue().toString());
                omsFee = Integer.parseInt(dataSnapshot.child("oms").getValue().toString());
                obcFee = Integer.parseInt(dataSnapshot.child("obc").getValue().toString());
                scFee = Integer.parseInt(dataSnapshot.child("sc").getValue().toString());
                stFee = Integer.parseInt(dataSnapshot.child("st").getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
    }

    public void getTotalFee(View view) {
        //Toast.makeText(CompleteProfileActivity.this, genFee, Toast.LENGTH_SHORT).show();
        int fee=0;

        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.comp_general:
                if (checked)
                    totalFees = genFee;
                    caste = "gen";
                break;
            case R.id.comp_oms:
                if (checked)
                    totalFees = omsFee;
                    caste="oms";
                break;
            case R.id.comp_obc:
                if (checked)
                    totalFees = obcFee;
                    caste="obc";
                break;
            case R.id.comp_sc:
                if (checked)
                    totalFees = scFee;
                    caste="sc";
                break;
            case R.id.comp_st:
                if (checked)
                    totalFees = stFee;
                    caste="st";
                break;
            default:
                caste="gen";
                totalFees = genFee;
                break;
        }

    }

    public void updateView(){
        Intent nIntent = new Intent(CompleteProfileActivity.this, StudentActivity.class);
        startActivity(nIntent);
    }


}
