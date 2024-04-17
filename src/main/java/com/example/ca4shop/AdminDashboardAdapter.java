package com.example.ca4shop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdminDashboardAdapter extends RecyclerView.Adapter<AdminDashboardAdapter.AdminDashboardViewHolder> {

    private List<AdminDashboardItem> itemList;

    public AdminDashboardAdapter(List<AdminDashboardItem> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public AdminDashboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_dashboard_item, parent, false);
        return new AdminDashboardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminDashboardViewHolder holder, int position) {
        AdminDashboardItem item = itemList.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    static class AdminDashboardViewHolder extends RecyclerView.ViewHolder {

        private TextView itemNameTextView;

        public AdminDashboardViewHolder(@NonNull View itemView) {
            super(itemView);
            itemNameTextView = itemView.findViewById(R.id.itemNameTextView);
        }

        public void bind(AdminDashboardItem item) {
            itemNameTextView.setText(item.getItemName());
        }
    }
}
