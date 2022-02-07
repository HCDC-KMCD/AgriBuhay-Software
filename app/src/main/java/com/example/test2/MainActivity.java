package com.example.test2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    EditText name, email, user, pass;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = findViewById(R.id.txtName);
        pass = findViewById(R.id.txtPass);
        name = findViewById(R.id.txtName);
//        phone = findViewById(R.id.txtContact);
        email = findViewById(R.id.txtEmail);

        btn = findViewById(R.id.btnRegister);

        DBReg dbReg = new DBReg();

        btn.setOnClickListener(v->{
            Registration reg = new Registration();

//            long phoneNum = Integer.parseInt(phone.getText().toString());

            reg.setFullname(name.getText().toString());
            reg.setEmail(email.getText().toString());

//            reg.setPhone(phoneNum);

            reg.setUsername(user.getText().toString());
            reg.setPassword(pass.getText().toString());

            dbReg.add(reg).addOnSuccessListener(suc->{
                Toast.makeText(this, "Record is inserted", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(er->{
                Toast.makeText(this, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
            });
        });
    }
}