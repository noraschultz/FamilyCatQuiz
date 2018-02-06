package com.example.android.familycatquiz;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    //This provides the option to save the score when the device is rotated [but I don't yet understand quite how it works!].
    static final String STATE_SCORE = "score";
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //This method prevents the initial focus of the app to go straight to the first text entry field and keps the keyboard hidden until that field is clicked.
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );

        //This method hides the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the player's current score state
        savedInstanceState.putInt(STATE_SCORE, score);

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);

        // Restore state members from saved instance
        score = savedInstanceState.getInt(STATE_SCORE);

        String scoreMessage = createScoreMessage(playerName, score);

        displayMessage(scoreMessage);
    }



    /**
     * This method is called when the submit answers button is clicked.
     */
    public void submitAnswers(View view) {

        // Get the player's name
        EditText nameTextField = findViewById(R.id.name_text_field);
        String playerName = nameTextField.getText().toString();

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

            if (boxLover.equals("Zwerg")) {
                score = score + 1;
            }

            // Check if player got the name of the cake loving cat correct.
            EditText cakeTextField = findViewById(R.id.cake_text_field);
            String cakeLover = cakeTextField.getText().toString();

            if (cakeLover.equals("Plopsi")) {
                score = score + 1;
            }

            // Check if player got the name of the chili loving cat correct.
            EditText chiliTextField = findViewById(R.id.chili_text_field);
            String chiliLover = chiliTextField.getText().toString();

            if (chiliLover.equals("Floh")) {
                score = score + 1;
            }

      }

String scoreMessage = createScoreMessage(playerName, score);

        displayMessage(scoreMessage);
    }

    /**
     * This method creates the score message.
     *
     * @param playerName gives the player's name
     * @param score of the quiz
     * @return text summary
     *
     */
    private String createScoreMessage (String playerName,int score) {
        String scoreMessage = "Hello " + playerName + ",";
        scoreMessage += "\nyou scored " + score + " points!";
        return scoreMessage;
    }

    /**
     * This method displays the score on the screen.
     */
    private void displayMessage(String scoreMessage) {
        TextView scoreTextView = (TextView) findViewById(R.id.score_text_view);
        scoreTextView.setText(scoreMessage);
    }

}
