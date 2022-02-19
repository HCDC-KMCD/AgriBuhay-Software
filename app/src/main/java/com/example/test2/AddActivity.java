package com.example.test2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class AddActivity extends AppCompatActivity {

    private MaterialEditText businessName, ownerName, businessAddress, businessNumber, businessEmail, datePicker;
    private Button addProducts;
    private ProgressBar loadingPB;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private String productID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        businessName = findViewById(R.id.txtBusinessName);
        ownerName = findViewById(R.id.txtOwnerName);
        businessAddress = findViewById(R.id.txtBusinessAdd);
        businessNumber = findViewById(R.id.txtContactNum);
        businessEmail = findViewById(R.id.txtBusinessEmail);
        datePicker = findViewById(R.id.datePicker);
        addProducts = findViewById(R.id.btnAddProducts);
        loadingPB = findViewById(R.id.progressBar3);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Products");

        addProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String bName = businessName.getText().toString();
                final String oName = ownerName.getText().toString();
                final String bAddress = businessAddress.getText().toString();
                final String bNum = businessNumber.getText().toString();
                final String bEmail = businessEmail.getText().toString();
                final String dPicker = datePicker.getText().toString();
                productID = bName;

                ProductModel productModel = new ProductModel(bName, oName, bAddress, bNum, bEmail, dPicker, productID);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseReference.child(productID).setValue(productModel);
                        Toast.makeText(AddActivity.this, "Product Added..", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AddActivity.this, HomeActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(AddActivity.this, "Error is"+error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}