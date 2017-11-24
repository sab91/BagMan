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

        ArrayAdapter arr = new ArrayAdapter(this, R.layout.activity_page_list, R.id.item_page, pdao.getList());
        ListView page_lv = (ListView) findViewById(android.R.id.list);
        page_lv.setAdapter(arr);
        page_lv.setOnItemClickListener(item_action);
    }

    private AdapterView.OnItemClickListener item_action = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            final Page page = (Page) parent.getItemAtPosition(position);
            Intent page_itemIntent = new Intent(Page_listActivity.this, Page_itemActivity.class);
            Bundle extras = new Bundle();

            extras.putInt("id", page.getId());
            page_itemIntent.putExtras(extras);
            startActivity(page_itemIntent);
        }
    };

    @Override
    public void onDestroy(){
        database.close();
        super.onDestroy();
    }
}
