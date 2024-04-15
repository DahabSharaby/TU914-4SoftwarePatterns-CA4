package com.example.ca4shop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private Context context;
    private List<Product> cartItemList;

    public CartAdapter(Context context, List<Product> cartItemList) {
        this.context = context;
        this.cartItemList = cartItemList;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Product product = cartItemList.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return cartItemList.size();
    }

    static class CartViewHolder extends RecyclerView.ViewHolder {
        private ImageView productImageView;
        private TextView productNameTextView, manufacturerTextView, priceTextView, categoryTextView;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            productImageView = itemView.findViewById(R.id.imageViewProduct);
            productNameTextView = itemView.findViewById(R.id.textViewProductName);
            manufacturerTextView = itemView.findViewById(R.id.textViewManufacturer);
            priceTextView = itemView.findViewById(R.id.textViewPrice);
            categoryTextView = itemView.findViewById(R.id.textViewCategory);
        }

        public void bind(Product product) {
            productNameTextView.setText(product.getName());
            manufacturerTextView.setText(product.getManufacturer());
            priceTextView.setText(String.valueOf(product.getPrice()));
            categoryTextView.setText(product.getCategory());

            Glide.with(itemView.getContext())
                    .load(product.getImageURL())
                    .placeholder(R.drawable.placeholder_image)
                    .error(R.drawable.error_image)
                    .into(productImageView);
        }
    }
}
