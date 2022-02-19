package com.example.test2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements ProductRVAdapter.ProductClickInterface {

    private RecyclerView productRV;
    private ProgressBar loadingPB;
    private FloatingActionButton addFAB;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ArrayList<ProductModel> productModels;
    private RelativeLayout bottomSheetRL;
    private ProductRVAdapter productRVAdapter;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        productRV = findViewById(R.id.idProductModel);
        loadingPB = findViewById(R.id.progressBar5);
        addFAB = findViewById(R.id.idBtnAdd);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Products");
        productModels = new ArrayList<>();
        productRVAdapter = new ProductRVAdapter(productModels, this, this);
        productRV.setAdapter(productRVAdapter);
        addFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, AddActivity.class));
            }
        });

        getAllProducts();
    }

    private void getAllProducts() {

        productModels. clear();
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                loadingPB.setVisibility(View.GONE);
                productModels.add(snapshot.getValue(ProductModel.class));
                productRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                loadingPB.setVisibility(View.GONE);
                productRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                loadingPB.setVisibility(View.GONE);
                productRVAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                loadingPB.setVisibility(View.GONE);
                productRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onProductClickInterface(int position) {
        displayBottomSheet(productModels.get(position));

    }

    private void displayBottomSheet(ProductModel productModel) {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View layout = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_dialog, bottomSheetRL);
        bottomSheetDialog.setContentView(layout);
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        bottomSheetDialog.show();

        TextView productName = layout.findViewById(R.id.idTVProductName);
        TextView businessName = layout.findViewById(R.id.idTVBusinessName);
        TextView contactNum = layout.findViewById(R.id.idTVContact);
        TextView price = layout.findViewById(R.id.idTVPrice);
        ImageView productImage = layout.findViewById(R.id.idIVProduct);

        Button btnEdit = layout.findViewById(R.id.btnEditProduct);
        Button btnView = layout.findViewById(R.id.btnView);

        productName.setText(productModel.getProductID());
        businessName.setText(productModel.getbName());
        contactNum.setText(productModel.getbNum());
        price.setText(productModel.getbEmail());
        Picasso.get().load(productModel.getdPicker()).into(productImage); //Change this line to image

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, EditActivity.class);
                intent.putExtra("product", productModel);
                startActivity(intent);
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(productModel.getbAddress())); //Change this line to imagelink
                startActivity(intent);
            }
        });

    }

    public boolean onCreateOptionMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (@NonNull MenuItem item){
        int id = item.getItemId();
        switch(id){
            case R.id.userProfile:
                startActivity(new Intent(HomeActivity.this, StartActivity.class));
                finish();
            case R.id.logOut:
                Toast.makeText(this, "User Logged Out", Toast.LENGTH_SHORT).show();
                mAuth.signOut();
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}