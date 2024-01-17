package com.comp2601.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    //create variables
    private static final String TAG = MainActivity.class.getSimpleName();

    public static final String QUESTION_ID_AND_ANSWER = "main_activity_question_id_and_answer";
    private static final String ALL_QUESTIONS = "all_questions";
    private static final String CURRENT_INDEX = "current_index";
    private static final String ALL_ANSWERS = "all_answers";


    //create UI field variables
    private TextView mQuestionTextView;
    private Button mButtonA;
    private Button mButtonB;
    private Button mButtonC;
    private Button mButtonD;
    private Button mButtonE;
    private Button mPrevButton;
    private Button mNextButton;
    private Button mSubmitButton;

    private int questionIndex;
    private ArrayList<Question> questions;
    private HashMap<String, String> mAnswerWithId = new HashMap<String, String>();
    private String[] mAnswers = new String[10];
    private ArrayList<Button> buttonList = new ArrayList<Button>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mButtonA = (Button) findViewById(R.id.button_A);
        mButtonB = (Button) findViewById(R.id.button_B);
        mButtonC = (Button) findViewById(R.id.button_C);
        mButtonD = (Button) findViewById(R.id.button_D);
        mButtonE = (Button) findViewById(R.id.button_E);
        mPrevButton = (Button) findViewById(R.id.button_prev);
        mNextButton = (Button) findViewById(R.id.button_next);
        mSubmitButton = (Button) findViewById(R.id.button_submit);

        buttonList.add(mButtonA);
        buttonList.add(mButtonB);
        buttonList.add(mButtonC);
        buttonList.add(mButtonD);
        buttonList.add(mButtonE);


        mButtonA.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(TAG, "Button A Clicked");
                //store answer "A" to string array
                mAnswers[questionIndex] = "A";
                //get id of current question
                String id = questions.get(questionIndex).getIdString();
                //store id and answer to hashmap
                mAnswerWithId.put(id, "A");
                //change background color of selected button
                changeButtonColor(mButtonA);
            }
        });

        mButtonB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(TAG, "Button B Clicked");
                mAnswers[questionIndex] = "B";
                String id = questions.get(questionIndex).getIdString();
                mAnswerWithId.put(id, "B");
                changeButtonColor(mButtonB);
            }
        });

        mButtonC.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(TAG, "Button C Clicked");
                mAnswers[questionIndex] = "C";
                String id = questions.get(questionIndex).getIdString();
                mAnswerWithId.put(id, "C");
                changeButtonColor(mButtonC);

            }
        });

        mButtonD.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(TAG, "Button D Clicked");
                mAnswers[questionIndex] = "D";
                String id = questions.get(questionIndex).getIdString();
                mAnswerWithId.put(id, "D");
                changeButtonColor(mButtonD);
            }
        });

        mButtonE.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(TAG, "Button E Clicked");
                mAnswers[questionIndex] = "E";
                String id = questions.get(questionIndex).getIdString();
                mAnswerWithId.put(id, "E");
                changeButtonColor(mButtonE);
            }
        });


        mPrevButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(TAG, "Prev Button Clicked");
                //if not the first question, current index -1
                if(questionIndex > 0){
                    questionIndex--;
                }
                //set question and choices text
                mQuestionTextView.setText("" + (questionIndex+1) + ". " + questions.get(questionIndex).getmQuestionString());
                mButtonA.setText("A. " + questions.get(questionIndex).getmchoiceAString());
                mButtonB.setText("B. " + questions.get(questionIndex).getmchoiceBString());
                mButtonC.setText("C. " + questions.get(questionIndex).getmchoiceCString());
                mButtonD.setText("D. " + questions.get(questionIndex).getmchoiceDString());
                mButtonE.setText("E. " + questions.get(questionIndex).getmchoiceEString());

                //set the button color with current question index
                setButtonColor(questionIndex);
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(TAG, "Next Button Clicked");
                //if the last question, index set to 0
                questionIndex = (questionIndex < questions.size()-1) ? questionIndex + 1 : 0;
                //set question and choices text
                mQuestionTextView.setText("" + (questionIndex+1) + ". " + questions.get(questionIndex).getmQuestionString());
                mButtonA.setText("A. " + questions.get(questionIndex).getmchoiceAString());
                mButtonB.setText("B. " + questions.get(questionIndex).getmchoiceBString());
                mButtonC.setText("C. " + questions.get(questionIndex).getmchoiceCString());
                mButtonD.setText("D. " + questions.get(questionIndex).getmchoiceDString());
                mButtonE.setText("E. " + questions.get(questionIndex).getmchoiceEString());
                //set the button color with current question index
                setButtonColor(questionIndex);

            }
        });

        mSubmitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(TAG, "Submit Button Clicked");
                //load new activity that allows to input student name and student number
                Intent intent = new Intent(MainActivity.this, ProvidePersonalInfo.class);
                //put question id and user answer hashmap to the new activity
                intent.putExtra(QUESTION_ID_AND_ANSWER, mAnswerWithId);
                //start activity
                startActivity(intent);
            }
        });

        //initialize values
        questions = null;
        questionIndex = 0;

        //get state on the screen
        //if rotate the screen
        if(savedInstanceState != null){
            //get variables from original screen
            questionIndex = savedInstanceState.getInt(CURRENT_INDEX);
            questions = savedInstanceState.getParcelableArrayList(ALL_QUESTIONS);
            mAnswers = savedInstanceState.getStringArray(ALL_ANSWERS);
            setButtonColor(questionIndex);
        }else{
            //when screen is not rotate

            ArrayList<Question> parsedQuestion = null;

            //questions = parsedQuestion;

            //parse questions from xml file to arraylist object
            try {
                InputStream inputStream = getResources().openRawResource(R.raw.questions);
                BufferedReader bReader = new BufferedReader(new InputStreamReader(inputStream));
                parsedQuestion = Test.pullParseFrom(bReader);
                bReader.close();
            }catch (java.io.IOException e){
                e.printStackTrace();
            }

            //error handler when parsed questions are null
            if(parsedQuestion == null || parsedQuestion.isEmpty()){
                Log.i(TAG, "Error: Questions not parsed");
            }

            questions = parsedQuestion;
        }

        //initialize the first page of test
        if(questions != null && questions.size() > 0) {
            mQuestionTextView.setText("" + (questionIndex+1) + ". " + questions.get(questionIndex).getmQuestionString());
            mButtonA.setText("A. " + questions.get(questionIndex).getmchoiceAString());
            mButtonB.setText("B. " + questions.get(questionIndex).getmchoiceBString());
            mButtonC.setText("C. " + questions.get(questionIndex).getmchoiceCString());
            mButtonD.setText("D. " + questions.get(questionIndex).getmchoiceDString());
            mButtonE.setText("E. " + questions.get(questionIndex).getmchoiceEString());
        }
    }

    //when state changes, save current state
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putParcelableArrayList(ALL_QUESTIONS, questions);
        savedInstanceState.putInt(CURRENT_INDEX, questionIndex);
        savedInstanceState.putStringArray(ALL_ANSWERS, mAnswers);

        super.onSaveInstanceState(savedInstanceState);
    }

    //change selected button color while other unselected buttons remain the same color
    private void changeButtonColor(Button button){
        for(Button b: buttonList){
            //change the button color that user clicked
            if(b == button){
                b.setBackgroundColor(Color.parseColor("#FFB6C1"));
            }
            //set other button's background color to default value
            else{
                b.setBackgroundColor(Color.parseColor("#87CEFA"));
            }
        }
    }

    //set button color of the new question page, default if not answered yet
    private void setButtonColor(int index){
        //get user answer for the question with that index
        String userAnswer = mAnswers[index];

        //if user has not answered to that question, set background color of all buttons to default value
        if(userAnswer == null){
            mButtonA.setBackgroundColor(Color.parseColor("#87CEFA"));
            mButtonB.setBackgroundColor(Color.parseColor("#87CEFA"));
            mButtonC.setBackgroundColor(Color.parseColor("#87CEFA"));
            mButtonD.setBackgroundColor(Color.parseColor("#87CEFA"));
            mButtonE.setBackgroundColor(Color.parseColor("#87CEFA"));

        }else{
            //when user has choosen an answer for that question
            switch (userAnswer){

                //if user selected answer "A", change that button color
                case "A":
                    mButtonA.setBackgroundColor(Color.parseColor("#FFB6C1"));
                    mButtonB.setBackgroundColor(Color.parseColor("#87CEFA"));
                    mButtonC.setBackgroundColor(Color.parseColor("#87CEFA"));
                    mButtonD.setBackgroundColor(Color.parseColor("#87CEFA"));
                    mButtonE.setBackgroundColor(Color.parseColor("#87CEFA"));
                    break;

                //if user selected answer "B", change that button color
                case "B":
                    mButtonA.setBackgroundColor(Color.parseColor("#87CEFA"));
                    mButtonB.setBackgroundColor(Color.parseColor("#FFB6C1"));
                    mButtonC.setBackgroundColor(Color.parseColor("#87CEFA"));
                    mButtonD.setBackgroundColor(Color.parseColor("#87CEFA"));
                    mButtonE.setBackgroundColor(Color.parseColor("#87CEFA"));
                    break;

                //if user selected answer "C", change that button color
                case "C":
                    mButtonA.setBackgroundColor(Color.parseColor("#87CEFA"));
                    mButtonB.setBackgroundColor(Color.parseColor("#87CEFA"));
                    mButtonC.setBackgroundColor(Color.parseColor("#FFB6C1"));
                    mButtonD.setBackgroundColor(Color.parseColor("#87CEFA"));
                    mButtonE.setBackgroundColor(Color.parseColor("#87CEFA"));
                    break;

                //if user selected answer "D", change that button color
                case "D":
                    mButtonA.setBackgroundColor(Color.parseColor("#87CEFA"));
                    mButtonB.setBackgroundColor(Color.parseColor("#87CEFA"));
                    mButtonC.setBackgroundColor(Color.parseColor("#87CEFA"));
                    mButtonD.setBackgroundColor(Color.parseColor("#FFB6C1"));
                    mButtonE.setBackgroundColor(Color.parseColor("#87CEFA"));
                    break;

                //if user selected answer "E", change that button color
                case "E":
                    mButtonA.setBackgroundColor(Color.parseColor("#87CEFA"));
                    mButtonB.setBackgroundColor(Color.parseColor("#87CEFA"));
                    mButtonC.setBackgroundColor(Color.parseColor("#87CEFA"));
                    mButtonD.setBackgroundColor(Color.parseColor("#87CEFA"));
                    mButtonE.setBackgroundColor(Color.parseColor("#FFB6C1"));
                    break;

                //set default button color
                default:
                    mButtonA.setBackgroundColor(Color.parseColor("#87CEFA"));
                    mButtonB.setBackgroundColor(Color.parseColor("#87CEFA"));
                    mButtonC.setBackgroundColor(Color.parseColor("#87CEFA"));
                    mButtonD.setBackgroundColor(Color.parseColor("#87CEFA"));
                    mButtonE.setBackgroundColor(Color.parseColor("#87CEFA"));
            }

        }
    }
}