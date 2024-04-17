package com.example.ca4shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class ViewCartActivity extends AppCompatActivity {

    private RecyclerView recyclerViewCart;
    private CartAdapter cartAdapter;
    private List<Product> cartItemList;
    private FirebaseFirestore db;
    private Button checkoutButton;

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

        checkoutButton = findViewById(R.id.checkoutButton);
        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkout();
            }
        });

        retrieveProductsFromIntent();
    }

    private void retrieveProductsFromIntent() {
        if (getIntent() != null && getIntent().hasExtra("product_details")) {
            Product product = getIntent().getParcelableExtra("product_details");

            cartItemList.add(product);
            cartAdapter.notifyDataSetChanged();
        }
    }

    private void checkout() {

        Intent intent = new Intent(this, CheckoutActivity.class);
        intent.putParcelableArrayListExtra("cart_items", new ArrayList<>(cartItemList));
        startActivity(intent);
    }
}
