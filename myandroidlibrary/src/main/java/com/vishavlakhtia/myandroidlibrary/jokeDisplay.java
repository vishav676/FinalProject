package com.vishavlakhtia.myandroidlibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class jokeDisplay extends AppCompatActivity {
    public final static String INTENT_JOKE ="INTENT_JOKE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        String joke = getIntent().getStringExtra(INTENT_JOKE);

        TextView displayJoke = (TextView)findViewById(R.id.displayTextView);
        displayJoke.setText(joke);
    }
}
