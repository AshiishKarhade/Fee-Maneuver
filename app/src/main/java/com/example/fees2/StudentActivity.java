package com.example.fees2;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class StudentActivity extends AppCompatActivity {
    FirebaseAuth mAuth;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("student");

        final String userID = mAuth.getCurrentUser().getUid();

        final TextView stuName = findViewById(R.id.student_name);
        final TextView stuNetfee = findViewById(R.id.ntf);
        final TextView stuRemfee = findViewById(R.id.rmf);

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = null;
                for(DataSnapshot userDataSnapshot: dataSnapshot.getChildren()){
                    value = dataSnapshot.child(userID).getValue(String.class);
                }
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                stuName.setText(value);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        

    }
}
