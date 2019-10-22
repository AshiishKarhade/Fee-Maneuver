package com.example.fees2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class StudentActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    private FirebaseUser mUser;
    String casteName;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference myRef = database.getReference("student").child(userID);
        DatabaseReference feeRef = database.getReference("fees");

        final TextView stuName = findViewById(R.id.student_name);
        final TextView stuNetfee = findViewById(R.id.ntf);
        final TextView stuRemfee = findViewById(R.id.rmf);
        Button signout = findViewById(R.id.student_signout);

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //String value = null;
                    String name = dataSnapshot.child("name").getValue().toString();
                    String netfee = dataSnapshot.child("totalFees").getValue().toString();
                    String remfee = dataSnapshot.child("remainingFees").getValue().toString();

                    stuName.setText(name);
                    stuNetfee.setText(netfee);
                    stuRemfee.setText(remfee);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });



        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.getInstance().signOut();
                startActivity(new Intent(StudentActivity.this, MainActivity.class));
                finish();
            }
        });

        

    }
}
