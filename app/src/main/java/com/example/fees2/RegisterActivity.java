package com.example.fees2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        //return super.onCreateOptionsMenu(menu);
        final EditText log_email = findViewById(R.id.register_email);
        final EditText log_password = findViewById(R.id.register_password);
        Button signup = findViewById(R.id.register_signup_btn);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = log_email.getText().toString();
                String password = log_password.getText().toString();

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                   // Log.d(TAG, "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                   // updateUI(user);

                                    // Write a message to the database


                                    Toast.makeText(RegisterActivity.this, "Created account.", Toast.LENGTH_SHORT).show();
                                    Intent loginIntent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(loginIntent);


                                    if(STUDENT){
                                        Intent completeIntent = new Intent(getApplicationContext(), CompleteProfileActivity.class);
                                        startActivity(completeIntent);
                                    }


                                } else {
                                    Toast.makeText(RegisterActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


            }
        });

    }

}
