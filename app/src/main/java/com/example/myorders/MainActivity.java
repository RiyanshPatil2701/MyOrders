package com.example.myorders;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tabAllOrders;
    private TextView tabCompleted;
    private TextView tabCancelled;
    private TextView tabBookedAgain;
    private List<TextView> tabsList;

    private LinearLayout navHome, navOrders, navWallet, navProfile;
    private List<LinearLayout> navList;

    private OrderAdapter orderAdapter;
    private ArrayList<OrderModel> orderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerOrders = findViewById(R.id.recyclerOrders);
        recyclerOrders.setLayoutManager(new LinearLayoutManager(this));
        getWindow().setStatusBarColor(getResources().getColor(R.color.yellow_primary));
        orderList = new ArrayList<>();
        orderList.add(new OrderModel("Four Wheeler", "₹ 229.0", "05 Feb, 4:46 PM", "#ORD12345", "741, Gumanwara", "00, Main Rd, Shivaji Nagar, Jhansi, Uttar Pradesh 284001, India", "CANCELLED"));
        orderList.add(new OrderModel("Four Wheeler", "₹ 229.0", "05 Feb, 4:46 PM", "#ORD12346", "741, Gumanwara", "00, Main Rd, Shivaji Nagar, Jhansi, Uttar Pradesh 284001, India", "CANCELLED"));
        orderList.add(new OrderModel("Four Wheeler", "₹ 1515.0", "05 Feb, 4:46 PM", "#ORD12347", "332, Gumanwara", "GC72+GGV, Kamrari, Madhya Pradesh 475661, India", "CANCELLED"));

        orderAdapter = new OrderAdapter(orderList);
        recyclerOrders.setAdapter(orderAdapter);

        tabAllOrders = findViewById(R.id.tabAllOrders);
        tabCompleted = findViewById(R.id.tabCompleted);
        tabCancelled = findViewById(R.id.tabCancelled);
        tabBookedAgain = findViewById(R.id.tabBookedAgain);

        tabsList = new ArrayList<>();
        tabsList.add(tabAllOrders);
        tabsList.add(tabCompleted);
        tabsList.add(tabCancelled);
        tabsList.add(tabBookedAgain);

        navHome = findViewById(R.id.navHome);
        navOrders = findViewById(R.id.navOrders);
        navWallet = findViewById(R.id.navWallet);
        navProfile = findViewById(R.id.navProfile);

        navList = new ArrayList<>();
        if (navHome != null) navList.add(navHome);
        if (navOrders != null) navList.add(navOrders);
        if (navWallet != null) navList.add(navWallet);
        if (navProfile != null) navList.add(navProfile);

        EditText edtSearch = findViewById(R.id.edtSearch);
        LinearLayout btnFilter = findViewById(R.id.btnFilter);
        LinearLayout btnSort = findViewById(R.id.btnSort);
        View infoBanner = findViewById(R.id.infoBanner);
        ImageView imgClose = findViewById(R.id.imgClose);

        for (final TextView tabView : tabsList) {
            tabView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateTabHighlight(tabView);
                    String selectedText = tabView.getText().toString().trim();

                    if (selectedText.equalsIgnoreCase("Cancelled") || selectedText.equalsIgnoreCase("Booked Again")) {
                        orderAdapter.filterByStatus("CANCELLED");
                    } else if (selectedText.equalsIgnoreCase("Completed")) {
                        orderAdapter.filterByStatus("COMPLETED");
                    } else {
                        orderAdapter.filterByStatus("All Orders");
                    }

                    Toast.makeText(MainActivity.this, selectedText + " Selected", Toast.LENGTH_SHORT).show();
                }
            });
        }

        for (final LinearLayout navItem : navList) {
            navItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateNavHighlight(navItem);
                }
            });
        }

        if (navOrders != null) {
            updateNavHighlight(navOrders);
        }

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {}
        });

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Filter Options Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        btnSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Sort Options Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                infoBanner.setVisibility(View.GONE);
            }
        });
    }

    private void updateTabHighlight(TextView selectedTab) {
        for (TextView tab : tabsList) {
            if (tab == selectedTab) {
                tab.setBackgroundResource(R.drawable.bg_selected_tab);
                tab.setTypeface(null, Typeface.BOLD);
                tab.setTextColor(Color.BLACK);
            } else {
                tab.setBackgroundColor(Color.TRANSPARENT);
                tab.setTypeface(null, Typeface.NORMAL);
                tab.setTextColor(Color.DKGRAY);
            }
        }
    }

    private void updateNavHighlight(LinearLayout selectedNav) {
        int defaultColor = Color.BLACK;
        int activeColor = getResources().getColor(R.color.yellow_primary);

        for (LinearLayout nav : navList) {
            ImageView navIcon = null;
            TextView navText = null;

            for (int i = 0; i < nav.getChildCount(); i++) {
                View child = nav.getChildAt(i);
                if (child instanceof ImageView) {
                    navIcon = (ImageView) child;
                } else if (child instanceof TextView) {
                    navText = (TextView) child;
                }
            }

            if (nav == selectedNav) {
                if (navIcon != null) navIcon.setColorFilter(activeColor);
                if (navText != null) navText.setTextColor(activeColor);
            } else {
                if (navIcon != null) navIcon.setColorFilter(defaultColor);
                if (navText != null) navText.setTextColor(defaultColor);
            }
        }
    }
}