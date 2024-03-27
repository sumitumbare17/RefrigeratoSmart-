package com.example.smart_fridge_ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView txt_gm = findViewById(R.id.txt_gm);
        String text = txt_gm.getText().toString();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);

        Map<Character, Integer> colorMap = new HashMap<>();
        int[] colors = {Color.rgb(204,85,0), Color.GREEN, Color.RED, Color.MAGENTA, Color.YELLOW}; // Add more colors if needed

        // Assign a color to each unique capital letter
        int colorIndex = 0;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isUpperCase(c) && !colorMap.containsKey(c)) {
                colorMap.put(c, colors[colorIndex % colors.length]);
                colorIndex++;
            }
        }

        // Apply colors to the text
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isUpperCase(c)) {
                int color = colorMap.get(c);
                spannableStringBuilder.setSpan(new ForegroundColorSpan(color), i, i + 1, 0);
            }
        }

        // Set the modified text back to TextView
        txt_gm.setText(spannableStringBuilder);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_items:
                        // Handle Items item click
                        showToast("Items clicked");
                        return true;
                    case R.id.navigation_add_item:
                        // Handle Add Item item click
                        startActivity(new Intent(MainActivity.this,AddItem.class));
                        return true;
                    case R.id.navigation_shop:
                        // Handle Shop item click
                        showToast("Shop clicked");
                        return true;
                    case R.id.navigation_recipe:
                        // Handle Recipe item click
                        showToast("Recipe clicked");
                        return true;
                }
                return false;
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    }
