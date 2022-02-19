package com.example.test2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.metrics.LogSessionId;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class LoginActivity extends AppCompatActivity {

    private MaterialEditText email, pass;
    private ProgressBar progressBar;
    private TextView forgotpass;
    private Button login, register;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private UsersData usersData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        register = findViewById(R.id.btnReg);
        login = findViewById(R.id.btnLog);
        email = findViewById(R.id.txtEmail1);
        forgotpass = findViewById(R.id.forgotPass);
        pass = findViewById(R.id.txtPass1);
        progressBar = findViewById(R.id.progressBar2);

        firebaseAuth = FirebaseAuth.getInstance();

        register.setOnClickListener(v->{
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });

        login.setOnClickListener(vv->{
            String txtEmail = email.getText().toString();
            String txtPass = pass.getText().toString();

            if(TextUtils.isEmpty(txtEmail) || TextUtils.isEmpty(txtPass)){
                Toast.makeText(LoginActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
            }else{
                login(txtEmail, txtPass);
            }
        });

        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, ForgetPasswordActivity.class));
            }
        });

    }

    private void login(String txtEmail,String txtPass) {
        progressBar.setVisibility(View.VISIBLE);
        firebaseAuth.signInWithEmailAndPassword(txtEmail,txtPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if(task.isSuccessful()){
                    String uId = task.getResult().getUser().getUid();

                    firebaseDatabase = FirebaseDatabase.getInstance();
                    firebaseDatabase.getReference().child(uId).child("gender").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            usersData = snapshot.getValue(UsersData.class);
                            assert usersData != null;
                            if(usersData.getGender().equals("Producer")){
////                                Intent intent = new Intent(LoginActivity.this, ProducerActivity.class); //Make ProducerActivity
//                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                                startActivity(intent);
//                                finish();
                            }else if(usersData.getGender().equals("Logistics")){
//                                Intent intent = new Intent(LoginActivity.this, LogisticsActivity.class); // Make LogisticsActivity
//                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                                startActivity(intent);
//                                finish();
                            }else{
//                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
//                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                                startActivity(intent);
//                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
                    
                }else{
                    Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}