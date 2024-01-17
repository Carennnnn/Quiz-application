package com.comp2601.test;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Question implements Parcelable {

    private static final String TAG = Question.class.getSimpleName();

    public static final String XML_QUESTION = "question";
    public static final String XML_QUESTION_TEXT = "question_text";
    public static final String XML_CHOICE_A = "choiceA";
    public static final String XML_CHOICE_B = "choiceB";
    public static final String XML_CHOICE_C = "choiceC";
    public static final String XML_CHOICE_D = "choiceD";
    public static final String XML_CHOICE_E = "choiceE";
    public static final String XML_ID = "id";


    private String mId;
    private String mQuestionString;
    private String mchoiceAString;
    private String mchoiceBString;
    private String mchoiceCString;
    private String mchoiceDString;
    private String mchoiceEString;

    //constructor for Question
    public Question(String aid, String aQuestion, String aChoiceA, String aChoiceB, String aChoiceC, String aChoiceD, String aChoiceE){
        mId = aid;
        mQuestionString = aQuestion;
        mchoiceAString = aChoiceA;
        mchoiceBString = aChoiceB;
        mchoiceCString = aChoiceC;
        mchoiceDString = aChoiceD;
        mchoiceEString = aChoiceE;
    }

    //create parcelable arraylist
    protected Question(Parcel in) {
        mId = in.readString();
        mQuestionString = in.readString();
        mchoiceAString = in.readString();
        mchoiceBString = in.readString();
        mchoiceCString = in.readString();
        mchoiceDString = in.readString();
        mchoiceEString = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mId);
        dest.writeString(mQuestionString);
        dest.writeString(mchoiceAString);
        dest.writeString(mchoiceBString);
        dest.writeString(mchoiceCString);
        dest.writeString(mchoiceDString);
        dest.writeString(mchoiceEString);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    //get attribute for the current question
    public String getmQuestionString(){return mQuestionString;}
    public String getmchoiceAString(){return mchoiceAString;}
    public String getmchoiceBString(){return mchoiceBString;}
    public String getmchoiceCString(){return mchoiceCString;}
    public String getmchoiceDString(){return mchoiceDString;}
    public String getmchoiceEString(){return mchoiceEString;}
    public String getIdString(){return mId;}

    //example of a Question arraylist
    public static ArrayList<Question> example1(){
        ArrayList<Question> questions = new ArrayList<Question>();
        questions.add(new Question(
                "0",
                "How to kill an activity in Android?",
                "finish()",
                "finishActivity(int requestCode)",
                "A and B",
                "kill()",
                "None of the Above"
        ));
        return questions;
    }


}
