package com.example.ca4shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class ViewCartActivity extends AppCompatActivity {

    private RecyclerView recyclerViewCart;
    private CartAdapter cartAdapter;
    private List<Product> cartItemList;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart);

        recyclerViewCart = findViewById(R.id.recyclerViewCart);
        cartItemList = new ArrayList<>();
        db = FirebaseFirestore.getInstance();

        cartAdapter = new CartAdapter(this, cartItemList);
        recyclerViewCart.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCart.setAdapter(cartAdapter);

        retrieveProductsFromIntent();
    }

    private void retrieveProductsFromIntent() {
        if (getIntent() != null && getIntent().hasExtra("product_details")) {
            Product product = getIntent().getParcelableExtra("product_details");

            cartItemList.add(product);
            cartAdapter.notifyDataSetChanged();
        }
    }
}


