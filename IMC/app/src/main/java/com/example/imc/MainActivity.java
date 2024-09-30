// MainActivity.java
package com.example.imc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText weightEditText;
    private EditText heightEditText;
    private Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weightEditText = findViewById(R.id.weightEditText);
        heightEditText = findViewById(R.id.heightEditText);
        calculateButton = findViewById(R.id.calculateButton);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateIMC();
            }
        });
    }

    private void calculateIMC() {
        String weightString = weightEditText.getText().toString();
        String heightString = heightEditText.getText().toString();

        if (!weightString.isEmpty() && !heightString.isEmpty()) {
            double weight = Double.parseDouble(weightString);
            double height = Double.parseDouble(heightString);
            double imc = weight / (height * height);

            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            intent.putExtra("IMC_VALUE", imc);
            startActivity(intent);
        }
    }
}
