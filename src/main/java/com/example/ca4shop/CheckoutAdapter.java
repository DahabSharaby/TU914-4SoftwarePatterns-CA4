package com.example.ca4shop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CheckoutAdapter extends RecyclerView.Adapter<CheckoutAdapter.CheckoutViewHolder> {

    private Context context;
    private List<Product> cartItemList;

    public CheckoutAdapter(Context context, List<Product> cartItemList) {
        this.context = context;
        this.cartItemList = cartItemList;
    }

    @NonNull
    @Override
    public CheckoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new CheckoutViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CheckoutViewHolder holder, int position) {
        Product product = cartItemList.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return cartItemList.size();
    }

    static class CheckoutViewHolder extends RecyclerView.ViewHolder {
        private TextView productNameTextView, manufacturerTextView, priceTextView, quantityTextView;

        public CheckoutViewHolder(@NonNull View itemView) {
            super(itemView);
            productNameTextView = itemView.findViewById(R.id.textViewProductName);
            manufacturerTextView = itemView.findViewById(R.id.textViewManufacturer);
            priceTextView = itemView.findViewById(R.id.textViewPrice);
            quantityTextView = itemView.findViewById(R.id.textViewStockQuantity);
        }

        public void bind(Product product) {
            productNameTextView.setText(product.getName());
            manufacturerTextView.setText(product.getManufacturer());
            priceTextView.setText(product.getPrice());
            quantityTextView.setText(String.valueOf(product.getStockQuantity()));
        }
    }
}
