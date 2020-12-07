package com.example.aplikasiotomotif;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class MainUpdel extends AppCompatActivity {
    private MyDatabase db;
    private String Sid, Snama, Spabrikan, Scc;
    private EditText Enama, Epabrikan, Ecc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_updel);
        db = new MyDatabase(this);
        Intent i = this.getIntent();
        Sid = i.getStringExtra("Iid");
        Snama = i.getStringExtra("Inama");
        Spabrikan = i.getStringExtra("Ipabrikan");
        Scc = i.getStringExtra("Icc");
        Enama = (EditText) findViewById(R.id.updel_nama);
        Epabrikan = (EditText) findViewById(R.id.updel_pabrikan);
        Ecc = (EditText) findViewById(R.id.updel_cc);
        Enama.setText(Snama);
        Epabrikan.setText(Spabrikan);
        Ecc.setText(Scc);
        Button btnUpdate = (Button) findViewById(R.id.btn_up);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snama = String.valueOf(Enama.getText());
                Spabrikan = String.valueOf(Epabrikan.getText());
                Scc = String.valueOf(Ecc.getText());
                if (Snama.equals("")){
                    Enama.requestFocus();
                    Toast.makeText(MainUpdel.this, "Silahkan isi nama",
                            Toast.LENGTH_SHORT).show();
                } else if (Spabrikan.equals("")){
                    Epabrikan.requestFocus();
                    Toast.makeText(MainUpdel.this, "Silahkan isi Pabrikan",
                            Toast.LENGTH_SHORT).show();
                }
                else if (Scc.equals("")){
                    Ecc.requestFocus();
                    Toast.makeText(MainUpdel.this, "Silahkan isi cc",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    db.UpdateOtomotif(new otomotif(Sid, Snama, Spabrikan, Scc));
                    Toast.makeText(MainUpdel.this, "Data telah diupdate",
                            Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
        Button btnDelete = (Button) findViewById(R.id.btn_del);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.DeleteOtomotif(new otomotif(Sid, Snama, Spabrikan, Scc));
                Toast.makeText(MainUpdel.this, "Data telah dihapus",
                        Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}

