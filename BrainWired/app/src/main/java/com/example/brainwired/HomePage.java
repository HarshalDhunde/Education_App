package com.example.brainwired;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity implements NavigationView
        .OnNavigationItemSelectedListener {

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


        List<examData> list = new ArrayList<>();
        list = getData();

        recyclerView
                = (RecyclerView) findViewById(
                R.id.recyclerView);
        listiner = new ClickListiner() {
            @Override
            public void click(int index) {
                Toast.makeText(getApplicationContext(), "clicked item index is " + index, Toast.LENGTH_LONG).show();
                if (index % 2 == 0){
//                    openYT();
                }
                else{
//                    openQuiz();
                }
            }
        };
        adapter = new ImageGallaryAdapter2(
                list, getApplication(), listiner);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(HomePage.this));

    }

    private void signoutt() {
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(HomePage.this, "Signed Out!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(HomePage.this, LoginPage.class);
        startActivity(intent);
    }

    ImageGallaryAdapter2 adapter;
    RecyclerView recyclerView;
    ClickListiner listiner;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    // Sample data for RecyclerView
    private List<examData> getData() {
        List<examData> list = new ArrayList<>();
        list.add(new examData("First Exam",
                "May 23, 2015",
                "Best Of Luck"));
        list.add(new examData("Second Exam",
                "June 09, 2015",
                "b of l"));
        list.add(new examData("My Test Exam",
                "April 27, 2017",
                "This is testing exam .."));

        return list;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    private void openYT(){
        Intent intent = new Intent(HomePage.this, Youtubepage.class);
//        intent.putExtra();
        startActivity(intent);
    }

}