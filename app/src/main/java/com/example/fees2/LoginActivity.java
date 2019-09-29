package com.example.fees2;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        EditText log_email = findViewById(R.id.login_email);
        EditText log_password = findViewById(R.id.login_password);
        Button login = findViewById(R.id.login_login_btn);



        String email = log_email.getText().toString();
        String password = log_password.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                           // Log.d(TAG, "signInWithEmail:success");
                            //FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);

                            /*if(STUDENT){
                                studentLogin();
                            }
                            if(TEACHER){
                                teacherLogin();
                            }
*/
                        } else {
                                Toast.makeText(LoginActivity.this, "Authentication failed.",Toast.LENGTH_SHORT).show();
                            }
                    }
                });



    }


    public void studentLogin(){
        Intent stuIntent = new Intent(getApplicationContext(), StudentActivity.class);
        startActivity(stuIntent);
    }

    public void teacherLogin(){
        Intent teaIntent = new Intent(getApplicationContext(), TeacherActivity.class);
        startActivity(teaIntent);
    }

}
