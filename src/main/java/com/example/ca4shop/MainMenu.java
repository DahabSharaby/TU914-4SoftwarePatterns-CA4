package com.example.ca4shop;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }
    public void onAddProductClick(View view) {
        Intent intent = new Intent(this, AddProductActivity.class);
        startActivity(intent);
    }

    public void onBrowseProductsClick(View view) {
        Intent intent = new Intent(this, BrowseProductsActivity.class);
        startActivity(intent);
    }

    public void onViewCartClick(View view) {
        Intent intent = new Intent(this, ViewCartActivity.class);
        startActivity(intent);
    }

    public void onViewProfileClick(View view) {
        Intent intent = new Intent(this, ViewProfileActivity.class);
        startActivity(intent);
    }
}
