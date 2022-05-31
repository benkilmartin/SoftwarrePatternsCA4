package com.example.softwarepatternsca4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginActivity extends AppCompatActivity {

    private String pdbName = "Users";
    private EditText email, password;
    private RelativeLayout loginButton;
    private TextView adminPanel, notadminPanel;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        email = findViewById(R.id.lemail);
        password = findViewById(R.id.lpassword);
        loginButton = findViewById(R.id.lbtn);
        firebaseAuth = FirebaseAuth.getInstance();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String qemail = email.getText().toString().trim();
                String qpassword = password.getText().toString().trim();

                if (qpassword.isEmpty() || qemail.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please insure username and password are filled out", Toast.LENGTH_SHORT).show();
                } else {
                    //login user
                    firebaseAuth.signInWithEmailAndPassword(qemail,qpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                // checkVerification();
                                if(qemail.equalsIgnoreCase("administrator@gmail.com")){
                                    startActivity(new Intent(loginActivity.this, AdminPanel.class));
                                }else{
                                    startActivity(new Intent(loginActivity.this, ShopHome.class));
                                }
                            }else{
                                Toast.makeText(getApplicationContext(), "Username and/or password not recognised", Toast.LENGTH_SHORT).show();

                            }

                        }
                    });
                }
            }
        });
    }


}