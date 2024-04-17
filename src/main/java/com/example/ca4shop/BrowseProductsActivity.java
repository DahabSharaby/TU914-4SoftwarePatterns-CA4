package com.example.ca4shop;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BrowseProductsActivity extends AppCompatActivity implements ProductAdapter.OnProductClickListener {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private List<Product> productList;
    private List<Product> filteredList;
    private FirebaseFirestore db;
    private EditText searchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_products);

        recyclerView = findViewById(R.id.recyclerViewProducts);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productList = new ArrayList<>();
        filteredList = new ArrayList<>();
        productAdapter = new ProductAdapter(this, filteredList, this);
        recyclerView.setAdapter(productAdapter);

        searchEditText = findViewById(R.id.searchEditText);
        db = FirebaseFirestore.getInstance();
        fetchProducts();

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void fetchProducts() {
        db.collection("products")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Product product = document.toObject(Product.class);
                                productList.add(product);
                            }
                            filteredList.addAll(productList);
                            productAdapter.notifyDataSetChanged();
                        } else {

                            Toast.makeText(BrowseProductsActivity.this, "Failed to fetch products", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void filter(String searchText) {
        filteredList.clear();
        for (Product product : productList) {
            if (product.getName().toLowerCase().contains(searchText.toLowerCase()) ||
                    product.getCategory().toLowerCase().contains(searchText.toLowerCase())) {
                filteredList.add(product);
            }
        }
        productAdapter.notifyDataSetChanged();
    }

    @Override
    public void onProductClick(Product product) {
        Intent intent = new Intent(this, ViewCartActivity.class);
        intent.putExtra("product_details", product);
        startActivity(intent);
    }

    @Override
    public void onRatingChanged(int position, float rating) {
        Product product = filteredList.get(position);
        Toast.makeText(this, "Product rated: " + product.getName() + ", Rating: " + rating, Toast.LENGTH_SHORT).show();
    }


}
