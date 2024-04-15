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

        // Set up RecyclerView
        cartAdapter = new CartAdapter(this, cartItemList);
        recyclerViewCart.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCart.setAdapter(cartAdapter);

        // Retrieve products from Firestore
        retrieveProducts();
    }

    private void retrieveProducts() {
        // Assuming you have a collection named "products" in Firestore
        db.collection("products")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    for (Product product : queryDocumentSnapshots.toObjects(Product.class)) {
                        cartItemList.add(product);
                    }
                    // Notify the adapter that data has changed
                    cartAdapter.notifyDataSetChanged();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(ViewCartActivity.this, "Failed to retrieve products: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
}
