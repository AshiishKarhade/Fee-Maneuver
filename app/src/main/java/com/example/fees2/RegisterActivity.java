package com.example.fees2;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
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
    Context mcontext;
    boolean isTeacher = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        //return super.onCreateOptionsMenu(menu);
        final EditText log_email = findViewById(R.id.register_email);
        final EditText log_password = findViewById(R.id.register_password);
        Button signup = findViewById(R.id.register_signup_btn);
        final RadioButton rbs  = findViewById(R.id.radio_student);
        final RadioButton rbt  = findViewById(R.id.radio_teacher);
        mcontext=RegisterActivity.this;

        final ProgressBar mpro = findViewById(R.id.register_progressBar);
        mpro.setVisibility(View.GONE);



        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mpro.setVisibility(View.VISIBLE);

                String email = log_email.getText().toString();
                String password = log_password.getText().toString();

                if(email.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Email cannot be Empty", Toast.LENGTH_SHORT).show();
                    mpro.setVisibility(View.GONE);
                }
                else if(password.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Password cannot be Empty", Toast.LENGTH_SHORT).show();
                    mpro.setVisibility(View.GONE);
                }
                else{
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (task.isSuccessful()) {

                                        Toast.makeText(mcontext, "Authentication Successfull.", Toast.LENGTH_SHORT).show();
                                        if(isTeacher){
                                            mpro.setVisibility(View.GONE);
                                            Intent tIntent = new Intent(mcontext, TeacherActivity.class);
                                            startActivity(tIntent);
                                            finish();
                                        }
                                        else{
                                            mpro.setVisibility(View.GONE);
                                            Intent completeIntent = new Intent(mcontext, CompleteProfileActivity.class);
                                            startActivity(completeIntent);
                                            finish();
                                        }


                                    } else {
                                        mpro.setVisibility(View.GONE);
                                        Toast.makeText(mcontext, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }

            }
        });

    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_teacher:
                if (checked)
                    isTeacher = true;
                    break;
            case R.id.radio_student:
                if (checked)
                    isTeacher = false;
                    break;
        }
    }

}
