package com.example.brainwired;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class HomePage extends AppCompatActivity {

    ImageView img;
    TextView tv1, tv2;
    Button signout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        signout = findViewById(R.id.SignOutB);

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signoutt();
            }
        });
    }

    private void signoutt() {
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(HomePage.this, "Signed Out!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(HomePage.this, LoginPage.class);
        startActivity(intent);
    }
}