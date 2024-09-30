// ResultActivity.java
package com.example.imc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    private TextView resultTextView;
    private TextView classificationTextView;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resultTextView = findViewById(R.id.resultTextView);
        classificationTextView = findViewById(R.id.classificationTextView);
        backButton = findViewById(R.id.backButton); // Inicializando o botão

        double imc = getIntent().getDoubleExtra("IMC_VALUE", 0);
        resultTextView.setText(String.format("IMC: %.2f", imc));
        classificationTextView.setText(getClassification(imc));

        // Definindo a ação do botão "Voltar"
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Fecha a Activity atual e volta para a anterior
            }
        });
    }

    private String getClassification(double imc) {
        if (imc < 18.5) {
            return "Classificação: Abaixo do peso";
        } else if (imc < 24.9) {
            return "Classificação: Peso normal";
        } else if (imc < 29.9) {
            return "Classificação: Sobrepeso";
        } else if (imc < 34.9) {
            return "Classificação: Obesidade grau 1";
        } else if (imc < 39.9) {
            return "Classificação: Obesidade grau 2";
        } else {
            return "Classificação: Obesidade grau 3";
        }
    }
}
