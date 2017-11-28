package fr.utt.if26.mytravel.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import fr.utt.if26.mytravel.Model.Page;
import fr.utt.if26.mytravel.Config.Bdd;
import fr.utt.if26.mytravel.DAO.PageDAO;
import fr.utt.if26.mytravel.R;

public class Page_listActivity extends AppCompatActivity {
    private Bdd database;
    private PageDAO pdao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_list);

        database = new Bdd(this);
        pdao = new PageDAO(database);

        ArrayAdapter arr = new ArrayAdapter(this, android.R.layout.simple_list_item_1, pdao.getList());
        ListView page_lv = (ListView) findViewById(R.id.list_page);
        page_lv.setAdapter(arr);
        page_lv.setOnItemClickListener(item_action);

        Button new_pageButton = (Button) findViewById(R.id.new_pageButton);
        new_pageButton.setOnClickListener(create_page);
    }

    private View.OnClickListener create_page = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent page_createIntent = new Intent(Page_listActivity.this, Page_createActivity.class);
            startActivity(page_createIntent);
        }
    };

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
