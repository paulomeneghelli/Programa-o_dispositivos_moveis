package com.example.sql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
    ArrayList<Integer> ids;


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
        ids = new ArrayList<>();


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
                    limparCampos();

                } else {
                    Toast.makeText(getApplicationContext(), "se lascou", Toast.LENGTH_LONG).show();
                }
                carregarListagem();
            }
        });

        database = openOrCreateDatabase("meubd", MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS pessoas (id INTEGER PRIMARY KEY AUTOINCREMENT,nome varchar, email varchar, datanasc Date)");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemId = ids.get(position);
                carregarDados(itemId);
            }
        });
    }

    public void carregarListagem(){
        Cursor cursor = database.rawQuery("SELECT * FROM pessoas ", null);
        cursor.moveToFirst();
        ArrayList<String> nomes = new ArrayList<>();
        while (!cursor.isAfterLast()){
            nomes.add(cursor.getString(1));
            ids.add(cursor.getInt(0));

            cursor.moveToNext();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, nomes);
        listView.setAdapter(adapter);
    }

    private void carregarDados(int id) {
        Cursor cursor = database.rawQuery("SELECT * FROM pessoas WHERE id = ?", new String[]{String.valueOf(id)});
        if (cursor.moveToFirst()) {
            editNome.setText(cursor.getString(1)); // Nome
            editEmail.setText(cursor.getString(2)); // Email
        }
        cursor.close();
    }

    private void limparCampos() {
        editNome.setText("");
        editEmail.setText("");
        editData.setText("");
    }
}