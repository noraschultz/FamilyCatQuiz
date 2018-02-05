package com.example.android.familycatquiz;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the submit answers button is clicked.
     */
    public void submitAnswers(View view) {

        // Get the player's name
        EditText nameTextField = findViewById(R.id.name_text_field);
        String userName = nameTextField.getText().toString();

        int score = 0;

        // Check if player thinks 1 cat lives in Berlin
        RadioButton q1RadioButton1 = findViewById(R.id.q1_radio1);
        boolean oneCatInBerlin = q1RadioButton1.isChecked();

        // Check if player thinks 2 cats live in Berlin
        RadioButton q1RadioButton2 = findViewById(R.id.q1_radio2);
        boolean twoCatsInBerlin = q1RadioButton2.isChecked();

        // Check if player thinks 3 cats live in Berlin
        RadioButton q1RadioButton3 = findViewById(R.id.q1_radio3);
        boolean threeCatsInBerlin = q1RadioButton3.isChecked();

        if (threeCatsInBerlin) {
          score = score + 1;

          // Check if player thinks the oldest and youngest cats are friends
          CheckBox q2CheckBox1 = findViewById(R.id.q2_check_box1);
          boolean oldestYoungestFriends = q2CheckBox1.isChecked();

          if (oldestYoungestFriends) {
              score = score + 1;
          }

            // Check if player thinks the Berlin boys are friends
            CheckBox q2CheckBox2 = findViewById(R.id.q2_check_box2);
            boolean berlinBoysFriends = q2CheckBox2.isChecked();

            if (berlinBoysFriends) {
                score = score + 1;
            }

            // Check if player got the name of the box loving cat correct.
            EditText boxTextField = findViewById(R.id.box_text_field);
            String boxLover = boxTextField.getText().toString();

            if (boxLover == "Zwerg") {
                score = score + 1;
            }

            // Check if player got the name of the cake loving cat correct.
            EditText cakeTextField = findViewById(R.id.cake_text_field);
            String cakeLover = cakeTextField.getText().toString();

            if (cakeLover == "Plopsi") {
                score = score + 1;
            }

            // Check if player got the name of the chili loving cat correct.
            EditText chiliTextField = findViewById(R.id.chili_text_field);
            String chiliLover = cakeTextField.getText().toString();

            if (cakeLover == "Floh") {
                score = score + 1;
            }






      }



        displayMessage(scoreMessage);
    }

    /**
     * This method creates the score message.
     *
     * @param userName gives the player's name
     * @param score of the quiz
     * @return text summary
     *
     */
    private String createScoreMessage (String userName,int score) {
        String scoreMessage = "Hello " + userName;
        scoreMessage += "\nYou scored " + score + " points!";
        return scoreMessage;

    /**
     * This method displays the score on the screen.
     */
    private void displayMessage(String scoreMessage) {
        TextView scoreTextView = (TextView) findViewById(R.id.score_text_view);
        scoreTextView.setText(scoreMessage);
    }

}
