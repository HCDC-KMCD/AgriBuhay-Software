package com.example.test2;

import android.content.Context;
import android.icu.text.Transliterator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductRVAdapter extends RecyclerView.Adapter<ProductRVAdapter.MyViewHolder1>{

    private ArrayList<ProductModel> productRVAdapterArrayList;
    private Context context;
    int lastPos = -1;
    private ProductClickInterface productClickInterface;


    public ProductRVAdapter(ArrayList<ProductModel> productRVAdapterArrayList, Context context, ProductClickInterface productClickInterface) {
        this.productRVAdapterArrayList = productRVAdapterArrayList;
        this.context = context;
        this.productClickInterface = productClickInterface;
    }

    @NonNull
    @Override
    public ProductRVAdapter.MyViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_rv_model, parent, false);

        return new MyViewHolder1(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductRVAdapter.MyViewHolder1 holder, final int position) {
        ProductModel productModel = productRVAdapterArrayList.get(position);
        holder.productNameTV.setText(productModel.getProductID());
        holder.priceTV.setText("Contact num: " + productModel.getbNum());
        Picasso.get().load(productModel.getbName()).into(holder.viewImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productClickInterface.onProductClickInterface(position);
            }
        });
    }

    private void setAnimation(View itemView, int position){
        if (position > lastPos){
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            itemView.setAnimation(animation);
            lastPos = position;
        }

    }

    @Override
    public int getItemCount() {
        return productRVAdapterArrayList.size();
    }

    public interface ProductClickInterface{
        void onProductClickInterface(int position);

    }

    public class MyViewHolder1 extends RecyclerView.ViewHolder {

        private TextView productNameTV, priceTV;
        private ImageView viewImage;

        public MyViewHolder1(@NonNull View itemView) {
            super(itemView);

            productNameTV = itemView.findViewById(R.id.tvProductName);
            priceTV = itemView.findViewById(R.id.idTVPrice);
            viewImage = itemView.findViewById(R.id.imgView);



        }
    }
}
