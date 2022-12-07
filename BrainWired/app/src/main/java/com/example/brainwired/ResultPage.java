package com.example.brainwired;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ResultPage extends AppCompatActivity {

    LinearLayout linearLayout;
    Intent intent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        ArrayList<QuestionChoiceVo> players = null;
        players = (ArrayList<QuestionChoiceVo>) args.getSerializable("ARRAYLIST");
        linearLayout= (LinearLayout) findViewById(R.id.linear1);
        prepareQuestionAnswerLayout(players);
    }

    private void prepareQuestionAnswerLayout(ArrayList<QuestionChoiceVo> questionChoiceVoArrayList) {
        for (QuestionChoiceVo mQuestionChoiceVo : questionChoiceVoArrayList) {
            LinearLayout mSingleQuestionLinearLayout = new LinearLayout(this);
            mSingleQuestionLinearLayout.setOrientation(LinearLayout.VERTICAL);
            mSingleQuestionLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            mSingleQuestionLinearLayout.setPadding(0,5,0,5);

            TextView mTextView = new TextView(this);
            TextView ansselected = new TextView(this);
            TextView ans = new TextView(this);

            mTextView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            mTextView.setText(mQuestionChoiceVo.getQuestion());
            mTextView.setTextSize(20f);
            mSingleQuestionLinearLayout.addView(mTextView);

            ansselected.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            ansselected.setText("Answer Selected : "+ mQuestionChoiceVo.getAns_selected());
            ansselected.setTextSize(15f);
            mSingleQuestionLinearLayout.addView(ansselected);

            ans.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            ans.setText("Correct Answer : "+ mQuestionChoiceVo.getAns());
            ans.setTextSize(15f);
            mSingleQuestionLinearLayout.addView(ans);
            linearLayout.addView(mSingleQuestionLinearLayout);
        }
        Button btn = new Button(this);
        btn.setText("home");
        btn.setEnabled(true);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(QuestionChoiceVo q: questionChoiceVoArrayList){
                    Log.d("Ans::::::", "onClick: "+q.getAns_selected());
                }
                Intent iintent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(iintent);
            }
        });
        linearLayout.addView(btn);
    }
}
