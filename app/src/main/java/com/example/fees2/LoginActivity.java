package com.example.fees2;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    boolean isTeacher = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        final EditText log_email = findViewById(R.id.login_email);
        final EditText log_password = findViewById(R.id.login_password);
        Button login = findViewById(R.id.login_login_btn);
        final RadioButton rbs  = findViewById(R.id.log_radio_student);
        final RadioButton rbt = findViewById(R.id.log_radio_teacher);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = log_email.getText().toString();
                String password = log_password.getText().toString();

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    loginSomewhere();

                                } else {
                                    Toast.makeText(LoginActivity.this, "Authentication failed.",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });



    }

    public void loginSomewhere(){
        if(isTeacher){
            Intent teaIntent = new Intent(getApplicationContext(), TeacherActivity.class);
            startActivity(teaIntent);
            finish();
        }
        else{
            Intent stuIntent = new Intent(getApplicationContext(), StudentActivity.class);
            startActivity(stuIntent);
            finish();
        }
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

    /*
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        loginSomewhere();
    }
    */

}
