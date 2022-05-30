package com.example.softwarepatternsca4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.softwarepatternsca4.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private EditText signupUsername,signupEmail,signupPassword,signupConfirmPassword;
    private Button registerButton;


    private FirebaseAuth firebaseAuth;
    private String userID;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        signupUsername=findViewById(R.id.rusernameinput);
        signupEmail=findViewById(R.id.remailinput);
        signupPassword=findViewById(R.id.rpasswordinput);
        signupConfirmPassword=findViewById(R.id.rpasswordinputauth);
        registerButton =findViewById(R.id.rbtn);


        firebaseAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String qusername=signupUsername.getText().toString().trim();
                String qemail=signupEmail.getText().toString().trim();
                String qpassword = signupPassword.getText().toString().trim();
                String qconfirmpassword = signupConfirmPassword.getText().toString().trim();

                if(qpassword.isEmpty() || qemail.isEmpty() || qusername.isEmpty() || qconfirmpassword.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please insure all fields are filled out", Toast.LENGTH_SHORT).show();
                }else if(qpassword.length()<7){
                    Toast.makeText(getApplicationContext(), "Password must be at least 7 characters", Toast.LENGTH_SHORT).show();

                }else if(!qpassword.equals(qconfirmpassword)){
                    Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();

                }else{

                    firebaseAuth.createUserWithEmailAndPassword(qemail,qpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                //Sending user data to realtime database
                                User user = new User(qusername,qemail,qpassword);
                                userID = firebaseAuth.getUid();
                                mDatabase.child("users").child(userID).setValue(user);
                                Toast.makeText(getApplicationContext(), "Succsess", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterActivity.this,MainActivity.class));
                                sendVerificationEmail();
                            }else{
                                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                                Log.e("error", "onComplete: Failed=" + task.getException().getMessage());

                            }

                        }
                    });
                }
            }
        });
    }

    private void sendVerificationEmail() {
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        if(firebaseUser!=null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(getApplicationContext(), "Verification Email Sent", Toast.LENGTH_SHORT).show();
                    firebaseAuth.signOut();
                    finish();
                }
            });
        }else{
            Toast.makeText(getApplicationContext(), "Verification Email Failed", Toast.LENGTH_SHORT).show();
        }
    }
}