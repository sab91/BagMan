package fr.utt.if26.mytravel.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import fr.utt.if26.mytravel.DAO.CarnetDAO;
import fr.utt.if26.mytravel.Helpers.MenuHeader;
import fr.utt.if26.mytravel.Helpers.PageAdapter;
import fr.utt.if26.mytravel.Model.Carnet;
import fr.utt.if26.mytravel.Model.Page;
import fr.utt.if26.mytravel.Config.Bdd;
import fr.utt.if26.mytravel.DAO.PageDAO;
import fr.utt.if26.mytravel.R;

public class Page_listActivity extends MenuHeader {
    private Bdd database;
    private int carnet_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_list);

        database = new Bdd(this);
        PageDAO pdao = new PageDAO(database);
        Bundle extras = getIntent().getExtras();
        carnet_id = extras.getInt("carnet_id");

        Log.e("===", ((Carnet) new CarnetDAO(database).getRow(carnet_id)).toStringWithPages());

        PageAdapter pa = new PageAdapter(this, R.layout.row_item, pdao.getRowByCarnet(carnet_id));

        ListView page_lv = (ListView) findViewById(R.id.list_page);
        page_lv.setAdapter(pa);
        page_lv.setOnItemClickListener(item_action);

        Button new_pageButton = (Button) findViewById(R.id.new_pageButton);
        new_pageButton.setOnClickListener(create_page);
    }

    private View.OnClickListener create_page = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent page_createIntent = new Intent(Page_listActivity.this, Page_createActivity.class);
            Bundle extras = new Bundle();
            extras.putInt("carnet_id", carnet_id);
            page_createIntent.putExtras(extras);
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