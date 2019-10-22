package com.example.fees2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateProfile extends AppCompatActivity {

    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        final String user_id = getIntent().getStringExtra("user_id");
        Button updatebtn = findViewById(R.id.update_fees_button);
        EditText newfee = findViewById(R.id.update_profile_fees_value);

        dbRef = FirebaseDatabase.getInstance().getReference().child("student").child(user_id).child("remainingFee");

        String nfee = newfee.getText().toString();
        writetoDatabase(nfee);

    }

    private void writetoDatabase(String nfee) {
        if(TextUtils.isEmpty(nfee)){
            Toast.makeText(this, "Cannot be empty", Toast.LENGTH_SHORT).show();
        }
        else{
            dbRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    int nwfee = Integer.parseInt(dataSnapshot.child("general").getValue().toString());
                    dbRef.setValue(nwfee);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }
}
