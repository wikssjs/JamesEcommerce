package com.ebookfrenzy.projetibj;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {


       private ProgressBar progress;
       private FirebaseAuth mAuth;
       private EditText edtEmail, edtUsername, edtPassword;
       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_activity);



           mAuth = FirebaseAuth.getInstance();

         edtUsername = findViewById(R.id.signup_username_edit);
         edtPassword = findViewById(R.id.signup_password_edi);
         edtEmail = findViewById(R.id.signup_email_edit);
         ImageButton back = findViewById(R.id.signup_back_button);
         progress = findViewById(R.id.progressBar);
           Button sigUp = findViewById(R.id.signup_signup_bouton);

           sigUp.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   registerUser();
               }
           });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, IntroActivity1.class);
                startActivity(intent);
            }
        });





    }


    private void registerUser(){
        String email = edtEmail.getText().toString().trim();
        String username = edtUsername.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        if(email.isEmpty()){
            edtEmail.setError("Email is required");
            edtEmail.requestFocus();
            return;
        }

        if(username.isEmpty()){
            edtUsername.setError("Username is required!");
            edtUsername.requestFocus();
            return;
        }
        if(username.length()<4){
            edtUsername.setError("Username should be a least 4 characters long!");
            edtUsername.requestFocus();
        }
        if(password.isEmpty()){
            edtPassword.setError("Password is required");
            edtPassword.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            edtEmail.setError("Email incorrect");
            edtEmail.requestFocus();
            return;
        }

        if(password.length()<6){
            edtPassword.setError("Password should be a least 6 characters long!");
            edtPassword.requestFocus();
            return;
        }

        progress.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(task -> {

                    if(task.isSuccessful()){
                        Client client = new Client(username,password,email);

                        FirebaseDatabase.getInstance().getReference("Users")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .setValue(client).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(SignUpActivity.this,"User has been registered succesfully!",Toast.LENGTH_LONG).show();
                                            progress.setVisibility(View.VISIBLE);

                                            startActivity(new Intent(SignUpActivity.this,MainActivity.class));
                                        }
                                        else{
                                            Toast.makeText(SignUpActivity.this,"Failed to register ! Try again",Toast.LENGTH_LONG).show();
                                            progress.setVisibility(View.VISIBLE);
                                        }
                                    }
                                });

                    }
                    else{
                        Toast.makeText(SignUpActivity.this,"Failed to register ! Try again",Toast.LENGTH_LONG).show();
                        progress.setVisibility(View.VISIBLE);
                    }
                });

    }
}