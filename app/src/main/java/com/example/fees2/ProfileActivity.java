package com.example.fees2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        final String user_id = getIntent().getStringExtra("user_id");

        final TextView stuName = findViewById(R.id.profile_name);
        final TextView stuNetfee = findViewById(R.id.profile_netfee);
        final TextView stuRemfee = findViewById(R.id.profile_remaining);
        Button updateBtn = findViewById(R.id.profile_update_button);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dbRef = database.getReference().child("student").child(user_id);

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //String value = null;
                String name = dataSnapshot.child("name").getValue().toString();
                //String casteName = dataSnapshot.child("caste").getValue().toString();
                String remfee = dataSnapshot.child("remainingFees").getValue().toString();
                String netfee = dataSnapshot.child("totalFees").getValue().toString();

                stuName.setText(name);
                stuNetfee.setText(netfee);
                stuRemfee.setText(remfee);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intee = new Intent(ProfileActivity.this, UpdateProfile.class);
                intee.putExtra("user_id", user_id);
                startActivity(intee);
            }
        });

    }
}
