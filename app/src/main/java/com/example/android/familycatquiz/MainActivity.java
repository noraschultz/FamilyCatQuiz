package com.example.android.familycatquiz;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    //This set up scoreMessage as a globally accessible String variable.
    private static String SCORE_MESSAGE = "scoreMessage";

    //This set up the scoreMessage as a globally accessible TextView.
    TextView scoreMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize the scoreMessage
        scoreMessage = findViewById(R.id.score_text_view);

        if (savedInstanceState != null) {
            CharSequence savedScoreMessage = savedInstanceState.getCharSequence(SCORE_MESSAGE);
            scoreMessage.setText(savedScoreMessage);}

        //This method prevents the initial focus of the app to go straight to the first text entry field and keps the keyboard hidden until that field is clicked.
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        //This method hides the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

    }

    /**
     * This method hides keyboard when EditText lose focus.
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View view = getCurrentFocus();
        if (view != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) && view instanceof EditText && !view.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            view.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + view.getLeft() - scrcoords[0];
            float y = ev.getRawY() + view.getTop() - scrcoords[1];
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom())
                ((InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((this.getWindow().getDecorView().getApplicationWindowToken()), 0);
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the player's current score state
        savedInstanceState.putCharSequence(SCORE_MESSAGE, scoreMessage.getText());

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);

        // Restore state members from saved instance
        scoreMessage.setText(savedInstanceState.getString(SCORE_MESSAGE));
    }



    /**
     * This method is called when the submit answers button is clicked.
     */
    public void submitAnswers(View view) {

        // Get the player's name
        EditText nameTextField = findViewById(R.id.name_text_field);
        String playerName = nameTextField.getText().toString();

        int score = 0;

        // Check if player thinks 3 cats live in Berlin
        RadioButton q1RadioButton3 = findViewById(R.id.q1_radio3);
        boolean threeCatsInBerlin = q1RadioButton3.isChecked();

        if (threeCatsInBerlin) {
            score = score + 1;
        }

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

        Toast.makeText(this, "Three cats live in Berlin.", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "The oldest and the youngest cat and Tikal and Tarantino are friends.", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Zwerg loves boxes.", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Plopsi loves cake.", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Floh loves chili.", Toast.LENGTH_SHORT).show();

        String scoreMessage = createScoreMessage(playerName, score);

        displayMessage(scoreMessage);

        // Find a reference to the bottom ImageView in the layout. Change the image.
        ImageView bottomImageView = findViewById(R.id.bottom_image_view);
        bottomImageView.setImageResource(R.drawable.cat_collage);

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
        scoreMessage += "\nyou scored " + score + " out of 6 points! Scroll down to see the correct answers in pictures.";
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
