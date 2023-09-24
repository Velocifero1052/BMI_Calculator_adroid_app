package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    TextView resultText;
    RadioButton radioButtonMale;
    RadioButton radioButtonFemale;
    EditText age;
    EditText feet;
    EditText inches;
    EditText weight;
    Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setButtonClickListener();
    }

    private void findViews() {
        resultText = findViewById(R.id.text_view_result);
        radioButtonMale = findViewById(R.id.radio_button_male);
        radioButtonFemale = findViewById(R.id.radio_buffon_female);
        age = findViewById(R.id.edit_text_age);
        feet = findViewById(R.id.edit_text_feet);
        inches = findViewById(R.id.edit_text_inches);
        weight = findViewById(R.id.edit_text_weight);
        calculateButton = findViewById(R.id.calculate_button);
    }

    private void setButtonClickListener() {
        calculateButton
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int age_ = Integer.parseInt(age.getText().toString());

                        double bmi = calculateBmi();

                        if (age_ >= 18) {
                            displayResult(bmi);
                        } else {
                            displayGuidance(bmi);
                        }
                    }
                });
    }

    private double calculateBmi() {
        String ageText = age.getText().toString();
        String inchText = inches.getText().toString();
        String feetText = feet.getText().toString();
        String weightText = weight.getText().toString();
        //converting from string
        int feet = Integer.parseInt(feetText);
        int age = Integer.parseInt(ageText);
        int inch = Integer.parseInt(inchText);
        int weight = Integer.parseInt(weightText);

        int totalInches = feet * 12 + inch;
        double heightInMeters = totalInches * 0.0254;
        return weight / (heightInMeters * heightInMeters);

    }

    private void displayResult(double bmi) {
        DecimalFormat formatter = new DecimalFormat("##.##"); //
        formatter.setRoundingMode(RoundingMode.DOWN);
        String bmiText = formatter.format(bmi);

        String res;
        if (bmi < 18.5) {
            res = bmiText + " - you are underweight!";
        } else if (bmi > 25) {
            res = bmiText + " - you are overweight!";
        } else {
            res = bmiText + " - you are in a great shape!";
        }
        resultText.setText(res);
    }

    private void displayGuidance(double bmi) {
        DecimalFormat formatter = new DecimalFormat("##.##"); //
        formatter.setRoundingMode(RoundingMode.DOWN);
        String bmiText = formatter.format(bmi);
        String fullResultString;
        if (radioButtonMale.isChecked()) {
            fullResultString = bmiText + " - as you are under 18, please consult with your doctor fro the healthy range for the boys";
        } else if (radioButtonFemale.isChecked()) {
            fullResultString = bmiText + " - as you are under 18, please consult with your doctor fro the healthy range for the girls";
        } else {
            fullResultString = bmiText + " - as you are under 18, please consult with your doctor fro the healthy range";
        }
        resultText.setText(fullResultString);
    }

}














