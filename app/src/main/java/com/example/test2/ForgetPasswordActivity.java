package com.example.test2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.rengwuxian.materialedittext.MaterialEditText;

public class ForgetPasswordActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private ProgressBar progressBar;
    private TextView resetMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Reset Password");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForgetPasswordActivity.this, LoginActivity.class));
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
        MaterialEditText emailAdd = findViewById(R.id.txtEmail2);
        resetMessage = findViewById(R.id.txtMessage);
        Button btnReset = findViewById(R.id.btnResetPass);
        progressBar = findViewById(R.id.progressBar1);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                firebaseAuth.fetchSignInMethodsForEmail(emailAdd.getText().toString()).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                    @Override
                    public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                        if(task.getResult().getSignInMethods().isEmpty()){
                            progressBar.setVisibility(View.GONE);
                            resetMessage.setText("This is not an registered email, you can create an account");
                        }else{
                            firebaseAuth.sendPasswordResetEmail(emailAdd.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    progressBar.setVisibility(View.GONE);
                                    if(task.isSuccessful()){
                                        resetMessage.setText("A reset email has been sent to your email address");
                                    }else{
                                        resetMessage.setText(task.getException().getMessage());
                                    }
                                }
                            });
                        }
                    }
                });
            }
        });

    }
}