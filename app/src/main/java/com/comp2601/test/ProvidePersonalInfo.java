package com.comp2601.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class ProvidePersonalInfo extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();

    private EditText mNameView;
    private EditText mNumberView;
    private Button mSendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provide_personal_info);

        mNameView = (EditText) findViewById(R.id.user_name_text_view);
        mNumberView = (EditText) findViewById(R.id.user_number_text_view);
        mSendButton = (Button) findViewById(R.id.email_button);

        //get user's answer and id hashmap from main activity
        HashMap<String, String> mAnswerWithId = (HashMap<String, String>) getIntent().getSerializableExtra(MainActivity.QUESTION_ID_AND_ANSWER);

        mSendButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(TAG, "Send Button Clicked");
                String studentName = mNameView.getText().toString().trim();
                String studentNumber = mNumberView.getText().toString().trim();
                //check if student has input both name and student number
                if(studentName.length() == 0 || studentNumber.length() == 0){
                    //if student lack of one element, show toast to notify student
                    Log.i(TAG, "lack of student name or student number");
                    Toast t  = Toast.makeText(ProvidePersonalInfo.this, "You need to enter your name and student number!", Toast.LENGTH_SHORT);
                    t.show();
                }else{
                    Log.i(TAG, "Sending email...");

                    String emailAddress = Test.EMAIL_ADDRESS;
                    String emailSubject = studentName + "'s " + "Midterm Test Answer";

                    //loop through hash map to create xml for the student's answers with question id
                    String xmlAnswer = "";
                    for(String i: mAnswerWithId.keySet()){
                        xmlAnswer += "      " + "<answer id=\"" + i + "\">" + mAnswerWithId.get(i) + "</answer>\n";
                    }

                    //create xml file for sending the email
                    String emailBody = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                            "<test>\n" +
                            "   <my_answer>\n"+
                            xmlAnswer +
                            "   </my_answer>\n" +
                            "   <student_name>" + studentName + "</student_name>\n"+
                            "   <student_number>" + studentNumber + "</student_number>\n"+
                            "</test>";

                    Log.i(TAG, emailBody);

                    //send email to the given email address
                    String emailURI = "mailto:" + emailAddress;
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse(emailURI));
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, emailSubject);
                    emailIntent.putExtra(Intent.EXTRA_TEXT, emailBody);

                    startActivity(Intent.createChooser(emailIntent, "Email Client..."));

                }
            }
        });

    }
}