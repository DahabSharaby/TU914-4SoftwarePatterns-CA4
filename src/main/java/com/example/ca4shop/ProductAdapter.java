package com.example.ca4shop;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> productList;
    private Context context;
    private OnProductClickListener clickListener;

    public ProductAdapter(Context context, List<Product> productList, OnProductClickListener clickListener) {
        this.context = context;
        this.productList = productList;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView productImageView;
        private TextView productNameTextView, manufacturerTextView, priceTextView, categoryTextView;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImageView = itemView.findViewById(R.id.imageViewProduct);
            productNameTextView = itemView.findViewById(R.id.textViewProductName);
            manufacturerTextView = itemView.findViewById(R.id.textViewManufacturer);
            priceTextView = itemView.findViewById(R.id.textViewPrice);
            categoryTextView = itemView.findViewById(R.id.textViewCategory);

            // Set click listener
            itemView.setOnClickListener(this);
        }

        public void bind(Product product) {
            productNameTextView.setText(product.getName());
            manufacturerTextView.setText(product.getManufacturer());
            priceTextView.setText(String.valueOf(product.getPrice()));
            categoryTextView.setText(product.getCategory());

            // Load image using Glide
            if (!TextUtils.isEmpty(product.getImageURL())) {
                Glide.with(itemView.getContext())
                        .load(product.getImageURL())
                        .placeholder(R.drawable.placeholder_image)
                        .error(R.drawable.error_image)
                        .into(productImageView);
            } else {
                productImageView.setImageResource(R.drawable.default_image);
            }
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    clickListener.onProductClick(productList.get(position));
                }
            }
        }
    }

    public interface OnProductClickListener {
        void onProductClick(Product product);

        void onRatingChanged(int position, float rating);
    }
}
