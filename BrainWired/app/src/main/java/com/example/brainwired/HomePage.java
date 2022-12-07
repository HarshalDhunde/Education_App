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
import java.util.Calendar;
import java.util.List;

public class HomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ImageView exampic, exampic2;
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

        exampic = findViewById(R.id.examPic);

        List<examData> list = new ArrayList<>();
        list = getData();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        listiner = new ClickListiner() {
            @Override
            public void click(int index) {
                Toast.makeText(getApplicationContext(), "clicked item index is " + index, Toast.LENGTH_LONG).show();
                if (index % 2 == 0) {
                    openYT();


                } else {
                    openQuiz();
                }
            }
        };
        adapter = new ImageGallaryAdapter2(list, getApplication(), listiner);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(HomePage.this));

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
        list.add(new examData("First Video", "May 23, 2015", "Best Of Luck"));
        list.add(new examData("First Quiz", "June 09, 2015", "b of l"));
        list.add(new examData("Second Video", "April 27, 2017", "This is testing exam .."));
        list.add(new examData("Second Quiz", "April 27, 2017", "This is testing exam .."));
        list.add(new examData("Third Video", "April 27, 2017", "This is testing exam .."));

        return list;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    private void openYT() {
        String dec = "Idol Season 13 is here to entertain you with its musical vocals and mesmerizing performances. This season is filled with talented people who are here to try their luck and show the world with their singing that they are here to spellbound all." + "Show Name – Indian Idol 13 Judges – Neha Kakkar, Himesh Reshammiya, Vishal Dadlani" + "Host – Aditya Narayan Episode No - 23";
        Intent intent = new Intent(HomePage.this, Youtubepage.class);
        Bundle bundle = new Bundle();
        bundle.putString("vidid", "Y40J_DomBu4");
        bundle.putString("desc", dec);
        intent.putExtra("yt_details", bundle);
        startActivity(intent);
    }

    private void openQuiz() {
        Calendar date = Calendar.getInstance();
        long timeInSecs = date.getTimeInMillis() + 1 * 60 * 1000;

        ArrayList<QuestionChoiceVo> questionChoiceVoArrayList = loadQuizData();

        Intent intent = new Intent(HomePage.this, TopicsPage.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Questions", questionChoiceVoArrayList);
        bundle.putLong("time", timeInSecs);
        intent.putExtra("Quiz_details", bundle);
        startActivity(intent);

    }

    private ArrayList<QuestionChoiceVo> loadQuizData() {
        ArrayList<QuestionChoiceVo> questionChoiceVoArrayList = new ArrayList<>();

        QuestionChoiceVo mQuestionChoiceVoOne = new QuestionChoiceVo("Question One", new ArrayList<String>() {{
            add("Choice One");
            add("Choice Two");
            add("Choice Three");
        }});
        QuestionChoiceVo mQuestionChoiceVoTwo = new QuestionChoiceVo("Question Two", new ArrayList<String>() {{
            add("Choice One");
            add("Choice Two");
            add("Choice Three");
        }});
        QuestionChoiceVo mQuestionChoiceVoThree = new QuestionChoiceVo("Question Three", new ArrayList<String>() {{
            add("Choice One");
            add("Choice Two");
            add("Choice Three");
        }});

        questionChoiceVoArrayList.add(mQuestionChoiceVoOne);
        questionChoiceVoArrayList.add(mQuestionChoiceVoTwo);
        questionChoiceVoArrayList.add(mQuestionChoiceVoThree);
        return questionChoiceVoArrayList;
    }
}