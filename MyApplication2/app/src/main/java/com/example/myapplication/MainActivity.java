package com.example.myapplication;

import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.View;
import android.content.Intent;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.message), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

//    public void btnOnClick(View view) {
//        // get value of button and spinner
//        TextView brands = findViewById(R.id.beerInformation);
//        Spinner color = findViewById(R.id.spinner);
//        String beerType = String.valueOf(color.getSelectedItem());
//
//        System.out.println(beerType);
//
//        // create new class beerExpert
//        beerExpert expert = new beerExpert();
//
//        // get list of brands
//        List<String> brandsList = expert.getBrands(beerType);
//
//        // build a string to store values of the list
//        StringBuilder build = new StringBuilder();
//        for (String i : brandsList) {
//            build.append(i).append('\n');
//        }
//
//        // display
//        brands.setText(build);
//    }
//
//    public static class beerExpert {
//        List<String> getBrands(String color) {
//            List<String> brands = new ArrayList<>() {};
//            if (color.equals("light")) {
//                brands.add("Jack Amber");
//                brands.add("Red Moose");
//            }
//            else {
//                brands.add("Jail");
//                brands.add("Gout");
//            }
//            return brands;
//        }
//    }

    public void onClickSend (View view) {
        EditText message = findViewById(R.id.editText);
        String messageText = message.getText().toString();

//        Uri image = Uri.parse("C://Users//PCM//Downloads");
//        Intent intentImg = new Intent(Intent.ACTION_SEND);
//        intentImg.setType("image/*");
//        intentImg.putExtra(Intent.EXTRA_STREAM, image);
//        intentImg.putExtra(Intent.EXTRA_SUBJECT, intentImg);

        // the message is the app user choose so don't need secondScreen.java

        if (!messageText.isEmpty()) {
            // action_send to send a message
            // action_dial to dial a number
            // action_web_search to perform a web search
            Intent intent = new Intent(Intent.ACTION_SEND);

            // need this for app
            // "text/plain" indicates that the data being shared is plain text
            intent.setType("text/plain");
            // intent.extra_text: the data is text
            intent.putExtra(Intent.EXTRA_TEXT, messageText);

            // the title of the message
            String chooserTitle = getString(R.string.chooser);

            // display
            Intent chosenIntent = Intent.createChooser(intent, chooserTitle);

            // always choose an activity
            startActivity(chosenIntent);

            // once or always
            // startActivity(intent);
        }
    }
}