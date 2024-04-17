package com.example.ca4shop;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdminDashboardActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdminDashboardAdapter adapter;
    private List<AdminDashboardItem> itemList;

    private EditText searchEditText;
    private Button manageStockButton;
    private Button manageCustomersButton;
    private Button simulatePurchasesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        searchEditText = findViewById(R.id.searchEditText);
        manageStockButton = findViewById(R.id.manageStockButton);
        manageCustomersButton = findViewById(R.id.manageCustomersButton);
        simulatePurchasesButton = findViewById(R.id.simulatePurchasesButton);

        recyclerView = findViewById(R.id.recyclerView);
        itemList = new ArrayList<>();
        adapter = new AdminDashboardAdapter(itemList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);



        manageStockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminDashboardActivity.this, "Manage Stock button clicked", Toast.LENGTH_SHORT).show();
            }
        });

        manageCustomersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminDashboardActivity.this, "Manage Customers button clicked", Toast.LENGTH_SHORT).show();
            }
        });

        simulatePurchasesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminDashboardActivity.this, "Simulate Purchases button clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
