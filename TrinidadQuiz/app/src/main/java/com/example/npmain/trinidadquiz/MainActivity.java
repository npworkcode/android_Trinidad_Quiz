package com.example.npmain.trinidadquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity
{
    private final static int NO_BUTTON_SELECTED = 0;
    private final static int NUMBER_OF_QUESTIONS = 5;
    private final static String CORRECT_ANSWERS = "correctAnswers";
    private final static String NUMBER_OF_CHECKBOXES_CHECKED = "selectedCheckBoxes";

    private final static int MAX_CHECKBOXES_SELECTED = 2;
    Button button;
    private int tallyOfCorrectAnswers = NO_BUTTON_SELECTED;
    private int numberOfSelectedCheckBoxes = NO_BUTTON_SELECTED;
    private RadioGroup[] groups = new RadioGroup[NUMBER_OF_QUESTIONS];
    private RadioButton[] correctAnswers = new RadioButton[NUMBER_OF_QUESTIONS];
    private CheckBox checkBox_1953;
    private CheckBox checkBox_1962;
    private CheckBox checkBox_1976;
    private CheckBox checkBox_1984;
    private EditText name;
    private Button resetButton;
    private Button scoreQuizButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        storeViewIds();

    }

    /* This method travels the Activity XML and retrieves the ids
    of the radio groups for each question and the ids for the radio buttons that have
    the correct answers. This is done to optimize the number of calls to findViewById
    Sets the listeners for the Checkboxes
    Sets the listeners for the Reset and Score Quiz buttons
     */
    private void storeViewIds()
    {
        name = findViewById(R.id.name_edit_text);
        groups[0] = findViewById(R.id.question_one_radiogroup);
        correctAnswers[0] = findViewById(R.id.question_one_choice_four);
        groups[1] = findViewById(R.id.question_two_radiogroup);
        correctAnswers[1] = findViewById(R.id.question_two_choice_one);
        groups[2] = findViewById(R.id.question_three_radiogroup);
        correctAnswers[2] = findViewById(R.id.question_three_choice_two);
        groups[3] = findViewById(R.id.question_four_radiogroup);
        correctAnswers[3] = findViewById(R.id.question_four_choice_three);
        groups[4] = findViewById(R.id.question_five_radiogroup);
        correctAnswers[4] = findViewById(R.id.question_five_choice_four);
        checkBox_1953 = findViewById(R.id.question_six_choice_one);
        checkBox_1962 = findViewById(R.id.question_six_choice_two);
        checkBox_1976 = findViewById(R.id.question_six_choice_three);
        checkBox_1984 = findViewById(R.id.question_six_choice_four);
        resetButton = findViewById(R.id.reset_quiz_button);
        scoreQuizButton = findViewById(R.id.score_quiz_button);
        setCheckBoxListener();
        setButtonListener();
    }

    /*
    This method sets the OnClickListener for all the checkboxes in Question six
    If the number of checkboxes for question six that are checked is 2, uncheck the current selection
    If the number is less than 2, check the selection and increment the counter
    If the checkbox is being unchecked, decrement the counter
     */
    private void setCheckBoxListener()
    {
        checkBox_1953.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (checkBox_1953.isChecked())
                {
                    if (numberOfSelectedCheckBoxes == MAX_CHECKBOXES_SELECTED)
                    {
                        checkBox_1953.setChecked(false);
                        Toast.makeText(MainActivity.this, R.string.checkbox_max_message, Toast.LENGTH_LONG).show();
                    }
                    else if (numberOfSelectedCheckBoxes < MAX_CHECKBOXES_SELECTED)
                    {
                        numberOfSelectedCheckBoxes++;
                    }
                }
                else
                {
                    if (numberOfSelectedCheckBoxes > 0) numberOfSelectedCheckBoxes--;
                }
            }
        });

        checkBox_1962.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (checkBox_1962.isChecked())
                {
                    if (numberOfSelectedCheckBoxes == MAX_CHECKBOXES_SELECTED)
                    {
                        checkBox_1962.setChecked(false);
                        Toast.makeText(MainActivity.this, R.string.checkbox_max_message, Toast.LENGTH_LONG).show();
                    }
                    else if (numberOfSelectedCheckBoxes < MAX_CHECKBOXES_SELECTED)
                    {
                        numberOfSelectedCheckBoxes++;
                    }
                }
                else
                {
                    if (numberOfSelectedCheckBoxes > 0) numberOfSelectedCheckBoxes--;
                }
            }
        });

        checkBox_1976.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (checkBox_1976.isChecked())
                {
                    if (numberOfSelectedCheckBoxes == MAX_CHECKBOXES_SELECTED)
                    {
                        checkBox_1976.setChecked(false);
                        Toast.makeText(MainActivity.this, R.string.checkbox_max_message, Toast.LENGTH_LONG).show();
                    }
                    else if (numberOfSelectedCheckBoxes < MAX_CHECKBOXES_SELECTED)
                    {
                        numberOfSelectedCheckBoxes++;
                    }
                }
                else
                {
                    if (numberOfSelectedCheckBoxes > 0) numberOfSelectedCheckBoxes--;
                }
            }
        });

        checkBox_1984.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (checkBox_1984.isChecked())
                {
                    if (numberOfSelectedCheckBoxes == MAX_CHECKBOXES_SELECTED)
                    {
                        checkBox_1984.setChecked(false);
                        Toast.makeText(MainActivity.this, R.string.checkbox_max_message, Toast.LENGTH_LONG).show();
                    }
                    else if (numberOfSelectedCheckBoxes < MAX_CHECKBOXES_SELECTED)
                    {
                        numberOfSelectedCheckBoxes++;
                    }
                }
                else
                {
                    if (numberOfSelectedCheckBoxes > 0) numberOfSelectedCheckBoxes--;
                }
            }
        });
    }

    /*
    This method sets the onClickListeners for the Reset and Score Quiz buttons
     */
    private void setButtonListener()
    {
        resetButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                resetQuiz(v);
            }
        });

        scoreQuizButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                scoreQuiz(v);
            }
        });
    }

    /*
    This function saves the number of correct answers for the Activity during an orientation change.
    @Param Bundle - data structure used to store the number of correct answers.
     */
    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        int numberOfCorrectAnswersSoFar = findCorrectAnswers();
        outState.putInt(CORRECT_ANSWERS, numberOfCorrectAnswersSoFar);
        outState.putInt(NUMBER_OF_CHECKBOXES_CHECKED, numberOfSelectedCheckBoxes);
    }

    /*
        This function restores the number of correct answers for the Activity after an orientation change.
        @Param Bundle - data structure used to store the number of correct answers.
     */
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null && savedInstanceState.containsKey(CORRECT_ANSWERS) && savedInstanceState.containsKey(NUMBER_OF_CHECKBOXES_CHECKED))
        {
            tallyOfCorrectAnswers = savedInstanceState.getInt(CORRECT_ANSWERS);
            numberOfSelectedCheckBoxes = savedInstanceState.getInt(NUMBER_OF_CHECKBOXES_CHECKED);
        }

    }


    /* This method resets the Quiz to its start state
     @param View - the reset button for the quiz
     */
    public void resetQuiz(View view)
    {
        for (int state = 0; state < NUMBER_OF_QUESTIONS; state++)
        {
            groups[state].clearCheck();
        }
        tallyOfCorrectAnswers = NO_BUTTON_SELECTED;
        numberOfSelectedCheckBoxes = NO_BUTTON_SELECTED;
        checkBox_1953.setChecked(false);
        checkBox_1962.setChecked(false);
        checkBox_1976.setChecked(false);
        checkBox_1984.setChecked(false);

    }

    /* This method scores how many questions are right, and stores the value in tallyOfCorrectAnswers
     @return int correct - the number of correct answers on the quiz
     */
    private int findCorrectAnswers()
    {
        int correct = 0;
        for (int state = 0; state < NUMBER_OF_QUESTIONS; state++)
        {
            int selectedRadioButtonId = groups[state].getCheckedRadioButtonId();
            if (correctAnswers[state] == findViewById(selectedRadioButtonId))
                correct++;
        }
        if ((checkBox_1962.isChecked()) && (checkBox_1976.isChecked()) && (!checkBox_1953.isChecked()) && (!checkBox_1984.isChecked()))
            correct++;
        return correct;
    }

    /* This method creates the message for the number of questions correct for the quiz
    @param View the Score Quiz button
     */
    public void scoreQuiz(View view)
    {
        tallyOfCorrectAnswers = findCorrectAnswers();
        String quizAnswer = getString(R.string.correct_answer_message, name.getText().toString(), tallyOfCorrectAnswers);
        Toast.makeText(this, quizAnswer, Toast.LENGTH_LONG).show();
    }
}
