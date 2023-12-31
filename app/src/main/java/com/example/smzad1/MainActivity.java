package com.example.smzad1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView questionTextBox;
    private Button trueButton;
    private Button falseButton;
    private Button nextButton;

    public Question[] questions = GetQuestions().toArray(new Question[0]);
    public int CurrentQuestionPoint = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trueButton = findViewById(R.id.trueButton);
        falseButton = findViewById(R.id.falseButton);
        nextButton = findViewById(R.id.nextButton);
        questionTextBox = findViewById(R.id.questionText001);

        questions = GetQuestions().toArray(new Question[0]);
        CurrentQuestionPoint = 0;

        trueButton.setBackgroundColor(getResources().getColor(R.color.Green_correct));
        falseButton.setBackgroundColor(getResources().getColor(R.color.Red_incorrect));

        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    CheckAnswer(questions[CurrentQuestionPoint], true);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    CheckAnswer(questions[CurrentQuestionPoint], false);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CurrentQuestionPoint = (CurrentQuestionPoint +1)%questions.length;
                SetNextQuestionContent();
            }
        });
        SetNextQuestionContent();

    }

    private void SetNextQuestionContent()
    {
        questionTextBox.setText(questions[CurrentQuestionPoint].QuestionContent);
        LinearLayout layout = (LinearLayout) findViewById(R.id.LinearBackground);
        layout.setBackgroundColor(getResources().getColor(R.color.background_idle));

        trueButton.setEnabled(true);
        falseButton.setEnabled(true);
        nextButton.setEnabled(false);
    }


    private void CheckAnswer(Question question ,boolean userAnswer) throws InterruptedException {
        int result = 0;
        String colorString;
        if(userAnswer == question.QuestionTrue)
        {
            result = R.string.correct_answer;
        }
        else
        {
            result = R.string.wrong_answer;
        }
        Toast toast = Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT);
        toast.show();

        trueButton.setEnabled(false);
        falseButton.setEnabled(false);

        nextButton.setEnabled(true);
    }

    private List<Question> GetQuestions()
    {
        List<Question> questionList = new ArrayList<Question>();

        questionList.add(Question.Create(R.string.question_001, true));
        questionList.add(Question.Create(R.string.question_002, true));
        questionList.add(Question.Create(R.string.question_003, false));
        questionList.add(Question.Create(R.string.question_004, false));
        questionList.add(Question.Create(R.string.question_005, true));
        questionList.add(Question.Create(R.string.question_006, false));
        questionList.add(Question.Create(R.string.question_007, false));
        questionList.add(Question.Create(R.string.question_008, true));
        questionList.add(Question.Create(R.string.question_009, true));

        return questionList;
    }
}