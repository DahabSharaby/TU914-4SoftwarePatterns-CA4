package com.example.ca4shop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class ViewProfileActivity extends AppCompatActivity {

    private TextView userEmailTextView;
    private EditText userNameEditText;
    private EditText userPhoneNumberEditText;
    private EditText userCityEditText;
    private EditText userPasswordEditText;
    private Button updateProfileButton;

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        userEmailTextView = findViewById(R.id.userEmailTextView);
        userNameEditText = findViewById(R.id.userNameEditText);
        userPhoneNumberEditText = findViewById(R.id.userPhoneNumberEditText);
        userCityEditText = findViewById(R.id.userCityEditText);
        userPasswordEditText = findViewById(R.id.userPasswordEditText);
        updateProfileButton = findViewById(R.id.updateProfileButton);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            // Display user email
            userEmailTextView.setText(currentUser.getEmail());

            // Fetch and display user information if available
            DocumentReference userRef = db.collection("users").document(currentUser.getUid());
            userRef.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Map<String, Object> userData = task.getResult().getData();
                    if (userData != null) {
                        userNameEditText.setText((String) userData.get("name"));
                        userPhoneNumberEditText.setText((String) userData.get("phoneNumber")); // Set phone number
                        userCityEditText.setText((String) userData.get("city"));
                    }
                } else {
                    Toast.makeText(ViewProfileActivity.this, "Failed to fetch user data", Toast.LENGTH_SHORT).show();
                }
            });
        }

        updateProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfile();
            }
        });
    }

    private void updateProfile() {
        String newName = userNameEditText.getText().toString().trim();
        String newPhoneNumber = userPhoneNumberEditText.getText().toString().trim();
        String newCity = userCityEditText.getText().toString().trim();
        String newPassword = userPasswordEditText.getText().toString().trim();

        // Update user information in Firestore
        if (currentUser != null) {
            DocumentReference userRef = db.collection("users").document(currentUser.getUid());
            Map<String, Object> updates = new HashMap<>();
            updates.put("name", newName);
            updates.put("phoneNumber", newPhoneNumber);
            updates.put("city", newCity);

            // Check if password is to be updated
            if (!TextUtils.isEmpty(newPassword)) {
                // Update password
                currentUser.updatePassword(newPassword)
                        .addOnSuccessListener(aVoid -> {
                            Toast.makeText(ViewProfileActivity.this, "Password updated successfully", Toast.LENGTH_SHORT).show();
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(ViewProfileActivity.this, "Failed to update password", Toast.LENGTH_SHORT).show();
                        });
            }

            // Update other user information
            userRef.update(updates)
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(ViewProfileActivity.this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(ViewProfileActivity.this, "Failed to update profile", Toast.LENGTH_SHORT).show();
                    });
        }
    }
}
