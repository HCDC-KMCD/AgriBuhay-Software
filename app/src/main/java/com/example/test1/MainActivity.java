package com.example.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username, password;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        username = (EditText) findViewById(R.id.username1);
        password = (EditText) findViewById(R.id.password1);
        Button Login = findViewById(R.id.Login);

        Button btnReg = findViewById(R.id.btnReg);

        DB = new DBHelper(this);

        btnReg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), regActivity.class);
                startActivity(intent);
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                if (user.equals("")||pass.equals("")){
                    Toast.makeText(MainActivity.this, "Please enter all required fields", Toast.LENGTH_SHORT).show();
                }else{
                    Boolean checkuserpass = DB.checkUsernamePassword(user, pass);
                    if (checkuserpass == true){
                        Toast.makeText(MainActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(MainActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void onRegistered (View register){

    }
}