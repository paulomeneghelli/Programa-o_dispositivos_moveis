package com.example.sql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase database;
    EditText editNome, editEmail, editData;
    Button button;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Associar variavis locais a views da interface
        editNome = findViewById(R.id.editNome);
        editEmail = findViewById(R.id.editEmail);
        editData = findViewById(R.id.editData);
        button = findViewById(R.id.buttonCadastrar);
        listView = findViewById(R.id.listView);

        //defininfo tratamnto para evento e click
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = editNome.getText().toString();
                String email = editEmail.getText().toString();
                ContentValues cv = new ContentValues();
                cv.put("nome", nome);
                cv.put("email", email);

                long status = database.insert("pessoas", null, cv);
                if (status>0){
                    Toast.makeText(getApplicationContext(), "gg win", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "se lascou", Toast.LENGTH_LONG).show();
                }
                carregarListagem();
            }
        });

        database = openOrCreateDatabase("meubd", MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS pessoas (id INTEGER PRIMARY KEY AUTOINCREMENT,nome varchar, email varchar, datanasc Date)");

    }

    public void carregarListagem(){
        Cursor cursor = database.rawQuery("SELECT * FROM pessoas ", null);
        cursor.moveToFirst();
        ArrayList<String> nomes = new ArrayList<>();
        while (!cursor.isAfterLast()){
            nomes.add(cursor.getString(1));
            cursor.moveToNext();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, nomes);
        listView.setAdapter(adapter);
    }
}