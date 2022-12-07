package com.example.brainwired;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;

public class TopicsPage extends AppCompatActivity {

    LinearLayout linearLayout;
    ArrayList selected = new ArrayList();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mcq_page);
        initialiseView();
    }

    private void initialiseView() {

        linearLayout = (LinearLayout) findViewById(R.id.questionsLinearLayout);

        ArrayList<QuestionChoiceVo> questionChoiceVoArrayList = new ArrayList<>();

        QuestionChoiceVo mQuestionChoiceVoOne = new QuestionChoiceVo("Question One", new ArrayList<String>() {{add("Choice One");add("Choice Two");add("Choice Three");}});
        QuestionChoiceVo mQuestionChoiceVoTwo = new QuestionChoiceVo("Question Two", new ArrayList<String>() {{add("Choice One");add("Choice Two");add("Choice Three");}});
        QuestionChoiceVo mQuestionChoiceVoThree = new QuestionChoiceVo("Question Three", new ArrayList<String>() {{add("Choice One");add("Choice Two");add("Choice Three");}});

        questionChoiceVoArrayList.add(mQuestionChoiceVoOne);
        questionChoiceVoArrayList.add(mQuestionChoiceVoTwo);
        questionChoiceVoArrayList.add(mQuestionChoiceVoThree);

        prepareQuestionAnswerLayout(questionChoiceVoArrayList);

    }

    private void prepareQuestionAnswerLayout(ArrayList<QuestionChoiceVo> questionChoiceVoArrayList) {
        for (QuestionChoiceVo mQuestionChoiceVo : questionChoiceVoArrayList) {
            LinearLayout mSingleQuestionLinearLayout = new LinearLayout(this);
            mSingleQuestionLinearLayout.setOrientation(LinearLayout.VERTICAL);
            mSingleQuestionLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            mSingleQuestionLinearLayout.setPadding(0,5,0,5);
            TextView mTextView = new TextView(this);
            mTextView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            mTextView.setText(mQuestionChoiceVo.getQuestion());
            mTextView.setTextSize(20f);
            mSingleQuestionLinearLayout.addView(mTextView);
            RadioGroup mChoiceRadioGroup = setUpChoices(mQuestionChoiceVo);

            RelativeLayout.LayoutParams radioGroupLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            mChoiceRadioGroup.setLayoutParams(radioGroupLayoutParams);
            mChoiceRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    mQuestionChoiceVo.setAns_selected(checkedId);
                }
            });
            mSingleQuestionLinearLayout.addView(mChoiceRadioGroup);
            linearLayout.addView(mSingleQuestionLinearLayout);
        }
        Button btn = new Button(this);
        btn.setText("Submit");
        btn.setEnabled(true);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(QuestionChoiceVo q: questionChoiceVoArrayList){
                    Log.d("Ans::::::", "onClick: "+q.getAns_selected());
                }
                Intent intent = new Intent(getApplicationContext(),ResultPage.class);
                Bundle args = new Bundle();
                args.putSerializable("ARRAYLIST",(Serializable)questionChoiceVoArrayList);
                intent.putExtra("BUNDLE",args);
                startActivity(intent);

            }
        });
        linearLayout.addView(btn);
    }

    private RadioGroup setUpChoices(QuestionChoiceVo mQuestionChoiceVo) {
        RadioGroup radioGroup = new RadioGroup(this);
        radioGroup.setId(View.generateViewId());

        for (int i = 0; i < mQuestionChoiceVo.getChoiceArrayList().size(); i++){
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(mQuestionChoiceVo.getChoiceArrayList().get(i));
            radioButton.setTextSize(18f);
            RadioGroup.LayoutParams radioGroupLayoutParams = new RadioGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            radioGroupLayoutParams.setMargins(10, 10, 10, 10);
            radioButton.setPadding(10, 10, 10, 10);
            radioButton.setLayoutParams(radioGroupLayoutParams);
            radioButton.setId(i+1);
            radioGroup.addView(radioButton);
        }
        return radioGroup;
    }



}
