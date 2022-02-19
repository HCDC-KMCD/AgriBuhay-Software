package com.example.test2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;
import java.util.Map;

public class EditActivity extends AppCompatActivity {

    private MaterialEditText editBusinessName, editOwnerName, editBusinessAdd, editContactNum, editBusinessEmail, editDate;
    private Button btnUpdate, btnDelete;
    private ProgressBar loadingPB;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String productID;
    private ProductModel productModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        editBusinessName = findViewById(R.id.editBusinessName);
        editOwnerName = findViewById(R.id.editOwnerName);
        editBusinessAdd = findViewById(R.id.editBusinessAdd);
        editContactNum = findViewById(R.id.editContactNum);
        editBusinessEmail = findViewById(R.id.editBusinessEmail);
        editDate = findViewById(R.id.editdatePicker);
        loadingPB = findViewById(R.id.progressBar4);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        productModel = getIntent().getParcelableExtra("Products");

        if(productModel != null){
            editBusinessName.setText(productModel.getbName());
            editOwnerName.setText(productModel.getoName());
            editBusinessAdd.setText(productModel.getbAddress());
            editContactNum.setText(productModel.getbNum());
            editBusinessEmail.setText(productModel.getbEmail());
            editDate.setText(productModel.getdPicker());
            productID = productModel.getProductID();
        }

        databaseReference = firebaseDatabase.getReference("Products").child(productID);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingPB.setVisibility(View.VISIBLE);
                String eBName = editBusinessName.getText().toString();
                String eOName = editOwnerName.getText().toString();
                String eBussAdd = editBusinessAdd.getText().toString();
                String eNum = editContactNum.getText().toString();
                String eEmail = editBusinessEmail.getText().toString();
                String eDate = editDate.getText().toString();

                Map<String, Object> map = new HashMap<>();
                map.put("bName",eBName);
                map.put("oName",eBName);
                map.put("bAddress",eBName);
                map.put("bNum",eBName);
                map.put("bEmail",eBName);
                map.put("dPicker",eBName);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        loadingPB.setVisibility(View.GONE);
                        Toast.makeText(EditActivity.this, "Product Successfully updated..", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(EditActivity.this, HomeActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(EditActivity.this, "Failed to update course", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteProduct();
            }
        });
    }

    private void deleteProduct(){
        databaseReference.removeValue();
        Toast.makeText(this, "Product Successfully deleted..", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(EditActivity.this, StartActivity.class));

    }
}