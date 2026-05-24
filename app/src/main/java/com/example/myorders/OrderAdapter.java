package com.example.myorders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    private List<OrderModel> originalList;
    private List<OrderModel> displayedList;

    public OrderAdapter(List<OrderModel> list) {
        this.originalList = list;
        this.displayedList = new ArrayList<>(list);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderModel order = displayedList.get(position);

        holder.txtVehicle.setText(order.getVehicleType());
        holder.txtPrice.setText(order.getPrice());
        holder.txtDate.setText(order.getDateTime());
        holder.txtOrderId.setText("  |  Order ID: " + order.getOrderId());
        holder.txtPickup.setText(order.getShortAddress());
        holder.txtDrop.setText(order.getFullAddress());
        holder.txtCancelled.setText(order.getStatus());
    }

    @Override
    public int getItemCount() {
        return displayedList.size();
    }

    // NEW METHOD: Custom filter logic for Tab Selection
    public void filterByStatus(String status) {
        displayedList.clear();

        if (status.equalsIgnoreCase("All Orders")) {
            displayedList.addAll(originalList); // Show everything back
        } else {
            for (OrderModel item : originalList) {
                // Matches "CANCELLED", "COMPLETED", etc.
                if (item.getStatus().equalsIgnoreCase(status)) {
                    displayedList.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtVehicle, txtPrice, txtDate, txtOrderId, txtPickup, txtDrop, txtCancelled;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtVehicle = itemView.findViewById(R.id.txtVehicle);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtOrderId = itemView.findViewById(R.id.txtOrderId);
            txtPickup = itemView.findViewById(R.id.txtPickup);
            txtDrop = itemView.findViewById(R.id.txtDrop);
            txtCancelled = itemView.findViewById(R.id.txtCancelled);
        }
    }
}