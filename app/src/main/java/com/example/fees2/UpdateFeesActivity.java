package com.example.fees2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateFeesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_fees);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("fees");

        Button updateFees = findViewById(R.id.update_updatebtn);
        final EditText generalFees = findViewById(R.id.update_general_text);
        final EditText omsFees = findViewById(R.id.update_oms_text);
        final EditText obcFees = findViewById(R.id.update_obc_text);
        final EditText scFees = findViewById(R.id.update_sc_text);
        final EditText stFees = findViewById(R.id.update_st_text);

        updateFees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String gf = generalFees.getText().toString();
                String omf = omsFees.getText().toString();
                String obf = obcFees.getText().toString();
                String scf = scFees.getText().toString();
                String stf = stFees.getText().toString();

                if(gf.isEmpty() || omf.isEmpty() || obf.isEmpty() || scf.isEmpty() || stf.isEmpty()){
                    Toast.makeText(UpdateFeesActivity.this, "One or more fields are empty", Toast.LENGTH_SHORT).show();
                }
                else{

                    int general = Integer.parseInt(generalFees.getText().toString());
                    int oms = Integer.parseInt(omsFees.getText().toString());
                    int obc = Integer.parseInt(obcFees.getText().toString());
                    int sc = Integer.parseInt(scFees.getText().toString());
                    int st = Integer.parseInt(stFees.getText().toString());

                    Fees fees = new Fees(general, oms, obc, sc, st);

                    myRef.setValue(fees);

                    updateView();
                }
            }
        });
    }

    private void updateView() {
        Intent update2Intent = new Intent(UpdateFeesActivity.this, TeacherActivity.class);
        startActivity(update2Intent);
    }
}
