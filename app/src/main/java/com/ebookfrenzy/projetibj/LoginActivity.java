package com.ebookfrenzy.projetibj;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginActivity extends AppCompatActivity {


    public static final String SHARED_PREFS = "sharedPrefs";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    SharedPreferences sharedPreferences;
    public static boolean check = false;
    private EditText edtEmail,edtPassword;
    private ProgressBar progress;
    private CheckBox checkBox ;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);


        mAuth = FirebaseAuth.getInstance();

        sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);

        checkBox = findViewById(R.id.checkBox);
        edtEmail = findViewById(R.id.login_email_edit);
        edtPassword = findViewById(R.id.login_password_edit);
        Button login = findViewById(R.id.login_login_button);
        progress = findViewById(R.id.login_progressBar);

        checkBox();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
                //startActivity(new Intent(LoginActivity.this, MainActivity.class));

            }
        });

    }

    private void checkBox() {
        String email = sharedPreferences.getString("email",null);

        if(email!=null){
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
        }
    }

    private void userLogin() {
        String email  = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();


        if(email.isEmpty()){
            edtEmail.setError("Email is required");
            edtEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            edtPassword.setError("Password is required");
            edtPassword.requestFocus();
            return;
        }

        if(password.length()<6){
            edtPassword.setError("Password should be a least 6 characters long!");
            edtPassword.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            edtEmail.setError("Email incorrect");
            edtEmail.requestFocus();
            return;
        }
        progress.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                   SharedPreferences.Editor editor = sharedPreferences.edit();
                   editor.putString(KEY_EMAIL,email);
                   editor.putString(KEY_PASSWORD,password);
                   editor.apply();

                   Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                   startActivity(intent);
                    Toast.makeText(LoginActivity.this,"logged succesfully",Toast.LENGTH_LONG).show();

                }
                else{
                    Toast.makeText(LoginActivity.this,"Failed to login ! Try again",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}


