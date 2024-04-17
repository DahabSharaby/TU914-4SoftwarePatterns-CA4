package com.example.ca4shop;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CheckoutActivity extends AppCompatActivity {

    private RecyclerView recyclerViewCheckout;
    private CheckoutAdapter checkoutAdapter;
    private ArrayList<Product> cartItemList;
    private Button payButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        cartItemList = getIntent().getParcelableArrayListExtra("cart_items");

        recyclerViewCheckout = findViewById(R.id.recyclerViewCheckout);
        payButton = findViewById(R.id.buttonPay);

        checkoutAdapter = new CheckoutAdapter(this, cartItemList);
        recyclerViewCheckout.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCheckout.setAdapter(checkoutAdapter);

        double totalPrice = calculateTotalPrice(cartItemList);
        TextView totalPriceTextView = findViewById(R.id.textViewTotalPrice);
        totalPriceTextView.setText("Total Price: $" + totalPrice);

        payButton.setOnClickListener(v -> {
            showAddressInputDialog();
        });
    }

    private void showAddressInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter Address");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("Confirm", (dialog, which) -> {
            String address = input.getText().toString().trim();
            if (!address.isEmpty()) {
                processPayment(address);
            } else {
                Toast.makeText(this, "Please enter a valid address", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    private void processPayment(String address) {
        Toast.makeText(this, "Payment successful!\nAddress: " + address, Toast.LENGTH_SHORT).show();
    }

    private double calculateTotalPrice(ArrayList<Product> cartItems) {
        double totalPrice = 0.0;
        for (Product product : cartItems) {
            totalPrice += Double.parseDouble(product.getPrice()) * product.getStockQuantity();
        }
        return totalPrice;
    }
}
