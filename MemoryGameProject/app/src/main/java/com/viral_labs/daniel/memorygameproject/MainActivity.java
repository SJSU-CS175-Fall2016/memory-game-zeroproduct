package com.viral_labs.daniel.memorygameproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void play(View view){
        Intent gameIntent = new Intent(this, GameActivity.class);
        startActivity(gameIntent);
    }

    public void rules(View view){
        Intent rulesIntent = new Intent(this, RulesActivity.class);
        startActivity(rulesIntent);
    }
}
