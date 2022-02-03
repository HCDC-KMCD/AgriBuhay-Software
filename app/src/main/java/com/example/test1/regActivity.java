package com.example.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class regActivity extends AppCompatActivity {

    EditText username, password, retypepassword;
    Button btnSignup, btnSignin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        retypepassword = (EditText) findViewById(R.id.retypepassword);

        btnSignin = (Button) findViewById(R.id.btnSignin);
        btnSignup = (Button) findViewById(R.id.btnSignup);

        DB = new DBHelper(this);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = retypepassword.getText().toString();

                if(user.equals("")||pass.equals("")||repass.equals(""))
                    Toast.makeText(regActivity.this, "Please the required fields", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        Boolean checkUser = DB.checkUsername(user);
                        if(checkUser == false){
                            Boolean insert = DB.insertData (user,pass);
                            if(insert == true){
                                Toast.makeText(regActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(regActivity.this, "Failed to register", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(regActivity.this, "User already exists! Please enter another name", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(regActivity.this, "Password does not match!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}