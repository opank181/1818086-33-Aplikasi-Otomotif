package com.example.aplikasiotomotif;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
public class MainRead extends AppCompatActivity implements
        AdapterView.OnItemClickListener{
    private ListView mListView;
    private CustomListAdapter adapter_off;
    private MyDatabase db;
    private List<otomotif> ListOtomotif = new ArrayList<otomotif>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_read);
        db = new MyDatabase(this);
        adapter_off = new CustomListAdapter(this, ListOtomotif );
        mListView = (ListView) findViewById(R.id.list_otomotif);
        mListView.setAdapter(adapter_off);
        mListView.setOnItemClickListener(this);
        mListView.setClickable(true);
        ListOtomotif.clear();
        List<otomotif> contacts = db.ReadOtomotif();
        for (otomotif cn : contacts) {
            otomotif judulModel = new otomotif();
            judulModel.set_id(cn.get_id());
            judulModel.set_nama(cn.get_nama());
            judulModel.set_pabrik(cn.get_pabrik());
            judulModel.set_cc(cn.get_cc());
            ListOtomotif.add(judulModel);
            if ((ListOtomotif.isEmpty()))
                Toast.makeText(MainRead.this, "Tidak ada data",
                        Toast.LENGTH_SHORT).show();
            else {
            }
        }
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
        Object o = mListView.getItemAtPosition(i);
        otomotif obj_itemDetails = (otomotif) o;
        String Sid = obj_itemDetails.get_id();
        String Snama = obj_itemDetails.get_nama();
        String Spabrikan = obj_itemDetails.get_pabrik();
        String Scc = obj_itemDetails.get_cc();
        Intent goUpdel = new Intent(MainRead.this, MainUpdel.class);
        goUpdel.putExtra("Iid", Sid);
        goUpdel.putExtra("Inama", Snama);
        goUpdel.putExtra("Ipabrikan", Spabrikan);
        goUpdel.putExtra("Icc", Scc);
        startActivity(goUpdel);
    }
    @Override
    protected void onResume() {
        super.onResume();
        ListOtomotif.clear();
        mListView.setAdapter(adapter_off);
        List<otomotif> contacts = db.ReadOtomotif();
        for (otomotif cn : contacts) {
            otomotif judulModel = new otomotif();
            judulModel.set_id(cn.get_id());
            judulModel.set_nama(cn.get_nama());
            judulModel.set_pabrik(cn.get_pabrik());
            judulModel.set_cc(cn.get_cc());
            ListOtomotif.add(judulModel);
            if ((ListOtomotif.isEmpty()))
                Toast.makeText(MainRead.this, "Tidak ada data",
                        Toast.LENGTH_SHORT).show();
            else {
            }
        }
    }
}

