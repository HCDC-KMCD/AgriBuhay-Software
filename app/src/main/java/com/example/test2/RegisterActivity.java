package com.example.test2;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;
import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {


    private MaterialEditText name, email, pass, phone;
    private RadioGroup radioGroup;
    private ProgressBar progressBar;
    private Button btn;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registeactivity);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Register");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(view -> {
            startActivity (new Intent(RegisterActivity.this, LoginActivity.class));
        });



        pass = findViewById(R.id.txtPass);
        name = findViewById(R.id.txtName);
        phone = findViewById(R.id.txtPhone);
        email = findViewById(R.id.txtEmail);
        progressBar = findViewById(R.id.progressBar);
        radioGroup = findViewById(R.id.radioButtons);

        btn = findViewById(R.id.btnRegister);



//        DBReg dbReg = new DBReg();


        btn.setOnClickListener(v->{
            final String full_name = name.getText().toString().trim();
            final String e_mail = email.getText().toString().trim();
            final String pass_word = pass.getText().toString().trim();
            final String num = phone.getText().toString().trim();

            int checkId = radioGroup.getCheckedRadioButtonId();

            RadioButton selected_gender = radioGroup.findViewById(checkId);

            if(selected_gender == null){
                Toast.makeText(RegisterActivity.this, "Select gender please", Toast.LENGTH_SHORT).show();
            }else {
                final String gender = selected_gender.getText().toString();

                if(TextUtils.isEmpty(full_name) || TextUtils.isEmpty(e_mail) || TextUtils.isEmpty(pass_word) ||
                        TextUtils.isEmpty(num)){
                    Toast.makeText(RegisterActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();

                }else{
                    register(full_name, e_mail, pass_word, num, gender);
                }
            }

        });

//        btn.setOnClickListener(v->{
//            Registration reg = new Registration();
//
//            reg.setFullname(name.getText().toString());
//            reg.setEmail(email.getText().toString());
//
//            reg.setPhone(phone.getText().toString());
//
//            reg.setPassword(pass.getText().toString());
//
//            dbReg.add(reg).addOnSuccessListener(suc->{
//                Toast.makeText(this, "Record is inserted", Toast.LENGTH_SHORT).show();
//            }).addOnFailureListener(er->{
//                Toast.makeText(this, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
//            });
//        });
    }

    private void register(final String full_name, final String e_mail, final String pass_word, final String num, final String gender) {
        progressBar.setVisibility(View.VISIBLE);
        firebaseAuth.createUserWithEmailAndPassword(e_mail,pass_word).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser rUser = firebaseAuth.getCurrentUser();
                    String userId = rUser.getUid();
                    db = FirebaseDatabase.getInstance().getReference("Users").child(userId);
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("userId", userId);
                    hashMap.put("full_name", full_name);
                    hashMap.put("e_mail", e_mail);
                    hashMap.put("pass_word", pass_word);
                    hashMap.put("num", num);
                    hashMap.put("gender", gender);
                    hashMap.put("ImageURL", "default");
                    db.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            progressBar.setVisibility(View.GONE);
                            if(task.isSuccessful()){
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                            }else{
                                Toast.makeText(RegisterActivity.this, Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }else{
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}