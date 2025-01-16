package com.example.starbuzz;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BuiThiLyActivity extends AppCompatActivity {

    public static final String EXTRA_TEXT = "Ly_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bui_thi_ly);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        int Lyid = (Integer) getIntent().getExtras().get(EXTRA_TEXT);
        BuiThiLy buiThiLy = BuiThiLy.ly[Lyid];

        TextView name = findViewById(R.id.LyName);
        name.setText(buiThiLy.getName());

        TextView age = findViewById(R.id.LyAge);
        age.setText(String.valueOf(buiThiLy.getAge()));

        ImageView photo = (ImageView) findViewById(R.id.LyPhoto);
        photo.setImageResource(buiThiLy.getId());
        photo.setContentDescription(buiThiLy.getName());
     }

}