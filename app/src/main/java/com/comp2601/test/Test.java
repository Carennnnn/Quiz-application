package com.comp2601.test;

import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.util.ArrayList;

public class Test {

    private static final String TAG = Test.class.getSimpleName();
    public static final String XML_EMAIL_ADDRESS = "email_address";
    public static String EMAIL_ADDRESS;


    public static ArrayList<Question> pullParseFrom(BufferedReader bReader){
        ArrayList<Question> questions = new ArrayList<Question>();

        XmlPullParserFactory factory = null;
        try{
            factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();

            xpp.setInput(bReader);
            int eventType = xpp.getEventType();

            String currentText = "";
            String currentQuestionText = "";
            String currentChoiceAText = "";
            String currentChoiceBText = "";
            String currentChoiceCText = "";
            String currentChoiceDText = "";
            String currentChoiceEText = "";
            String currentId = "";
            String tagName = "";

            //if it has not reached of the end line of the file
            while(eventType != XmlPullParser.END_DOCUMENT){
                //get current tag name
               tagName = xpp.getName();

               switch (eventType) {
                    case XmlPullParser.START_TAG:
                        //if tagname equals to "question", get current quesiton id
                        if(tagName.equalsIgnoreCase(Question.XML_QUESTION)){
                            currentId = xpp.getAttributeValue(null, Question.XML_ID);
                        }
                        break;

                    case XmlPullParser.TEXT:
                        //get current text between tag names
                        currentText = xpp.getText().trim();
                        break;

                    case XmlPullParser.END_TAG:
                        //if tagname equals to "question_text", set current question text
                        if(tagName.equalsIgnoreCase(Question.XML_QUESTION_TEXT)){
                            currentQuestionText = currentText;
                        //set choices text
                        }else if(tagName.equalsIgnoreCase(Question.XML_CHOICE_A)){
                            currentChoiceAText = currentText;
                        }else if(tagName.equalsIgnoreCase(Question.XML_CHOICE_B)){
                            currentChoiceBText = currentText;
                        }else if(tagName.equalsIgnoreCase(Question.XML_CHOICE_C)){
                            currentChoiceCText = currentText;
                        }else if(tagName.equalsIgnoreCase(Question.XML_CHOICE_D)){
                            currentChoiceDText = currentText;
                        }else if(tagName.equalsIgnoreCase(Question.XML_CHOICE_E)){
                            currentChoiceEText = currentText;
                        }else if(tagName.equalsIgnoreCase(Question.XML_QUESTION)){
                            //if it is end of a question, create a new Question objdct
                            questions.add(new Question(
                                    currentId,
                                    currentQuestionText,
                                    currentChoiceAText,
                                    currentChoiceBText,
                                    currentChoiceCText,
                                    currentChoiceDText,
                                    currentChoiceEText
                            ));
                        //if the tag name equals to "email_address", set email address
                        }else if(tagName.equalsIgnoreCase(XML_EMAIL_ADDRESS)){
                           EMAIL_ADDRESS = currentText;
                        }
                        break;

                   default:
                       break;
                }
                //iterate
                eventType = xpp.next();
            }
        }
        catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return questions;
    }
}
