package com.example.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private int seconds = 0;
    private boolean running;

    // default bundle is null

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
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
        }
        runTimer();
    }

    public void onClickStart (View view) {
        running = true;
    }

    public void onClickStop (View view) {
        running = false;
    }

    public void onClickReset (View view) {
        running = false;
        seconds = 0;
    }

    // todo: ROTATE DESTROYS THE ACTIVITY
    public void runTimer() {
        // this shit complex as fuck

        final TextView timeView = findViewById(R.id.time_view);

        // handler(): schedule code to run in the future
        final Handler handler = new Handler();

        // post(Runnable): run as soon as possible
        // postDelayed(Runnable, time): run as soon as possible after delay
        handler.post(new Runnable() {
            @Override
            public void run() {
                // format the seconds into h, m, s
                int hours = seconds / 3600, minutes = (seconds % 3600) / 60, secs = seconds % 60;
                String time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs);

                // set text to text view
                timeView.setText(time);

                if (running) {
                    seconds++;
                }

                // run after 0 millis delay
                handler.postDelayed(this, 0);
            }
        });
    }

    // SAVE BEFORE DESTROY
    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        // save values in a bundle

        // save seconds
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("seconds", seconds);

        // save running
        savedInstanceState.putBoolean("running", running);
    }

}