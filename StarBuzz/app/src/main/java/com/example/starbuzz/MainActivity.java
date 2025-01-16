package com.example.starbuzz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // list view does not button so can not use onClick
        // get items from list view using onItemClickListener and implement its onItemClick method
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                if (pos == 0) {
                    // create intent point to Drink.class
                    Intent intent = new Intent(MainActivity.this, DrinkCategory.class);
                    startActivity(intent);
                }
                else if (pos == 1) {
                    Intent intent = new Intent(MainActivity.this, BuiThiLyCategory.class);
                    startActivity(intent);
                }
            }
        };

        // set the listener to list view
        ListView listView = findViewById(R.id.list_option);
        listView.setOnItemClickListener(itemClickListener);
    }


}