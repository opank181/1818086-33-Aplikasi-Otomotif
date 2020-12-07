package com.example.aplikasiotomotif;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class MainCreate extends AppCompatActivity {
    private MyDatabase db;
    private EditText Enama, Epabrikan, Ecc;
    private String Snama, Spabrikan, Scc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_create);
        db = new MyDatabase(this);
        Enama = (EditText) findViewById(R.id.create_name);
        Epabrikan = (EditText) findViewById(R.id.create_pabrikan);
        Ecc = (EditText) findViewById(R.id.create_cc);
        Button btnCreate = (Button) findViewById(R.id.create_btn);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snama = String.valueOf(Enama.getText());
                Spabrikan = String.valueOf(Epabrikan.getText());
                Scc = String.valueOf(Ecc.getText());
                if (Snama.equals("")){
                    Enama.requestFocus();
                    Toast.makeText(MainCreate.this, "Silahkan isi nama",
                            Toast.LENGTH_SHORT).show();
                } else if (Spabrikan.equals("")){
                    Epabrikan.requestFocus();
                    Toast.makeText(MainCreate.this, "Silahkan isi Pabrikan",
                            Toast.LENGTH_SHORT).show();
                } else if (Scc.equals("")){
                    Ecc.requestFocus();
                    Toast.makeText(MainCreate.this, "Silahkan isi cc",
                            Toast.LENGTH_SHORT).show();
                }
                 else {
                    Enama.setText("");
                    Epabrikan.setText("");
                    Ecc.setText("");
                    Toast.makeText(MainCreate.this, "Data telah ditambah",
                            Toast.LENGTH_SHORT).show();
                    db.CreateOtomotif(new otomotif(null, Snama, Spabrikan, Scc));
                    Intent a = new Intent(MainCreate.this, MainActivity.class);
                    startActivity(a);
                }
            }
        });
    }
}

