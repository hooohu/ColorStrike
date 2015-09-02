package com.hackathon.coolblackfoot.colorstrike;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class WelcomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_page);
    }

    public void startGame(View nView) {
        Intent nIntent = new Intent(this, MainActivity.class);
        startActivity(nIntent);
    }

}
