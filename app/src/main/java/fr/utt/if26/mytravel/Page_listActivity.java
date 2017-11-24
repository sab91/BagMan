package fr.utt.if26.mytravel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import fr.utt.if26.mytravel.DAO.PageDAO;

public class Page_listActivity extends AppCompatActivity {
    private Bdd database;
    private PageDAO pdao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_list);

        database = new Bdd(this);
        pdao = new PageDAO(database);
    }

    @Override
    public void onDestroy(){
        database.close();
        super.onDestroy();
    }
}
