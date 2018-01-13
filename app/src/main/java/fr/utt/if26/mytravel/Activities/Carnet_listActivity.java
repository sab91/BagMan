package fr.utt.if26.mytravel.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import fr.utt.if26.mytravel.Config.Bdd;
import fr.utt.if26.mytravel.DAO.CarnetDAO;
import fr.utt.if26.mytravel.Helpers.CarnetAdapter;
import fr.utt.if26.mytravel.Helpers.MenuHeader;
import fr.utt.if26.mytravel.Model.Carnet;
import fr.utt.if26.mytravel.R;

public class Carnet_listActivity extends MenuHeader {
    private Bdd database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carnet_list1);

        database = new Bdd(this);
        CarnetDAO cdao = new CarnetDAO(database);

        CarnetAdapter ca = new CarnetAdapter(this, R.layout.row_item, cdao.getList());

        ListView carnet_lv = (ListView) findViewById(R.id.list_carnet);
        carnet_lv.setAdapter(ca);
        carnet_lv.setOnItemClickListener(item_action);

        Button new_carnetButton = (Button) findViewById(R.id.new_carnetButton);
        new_carnetButton.setOnClickListener(create_carnet);
    }

    private View.OnClickListener create_carnet = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent carnet_createIntent = new Intent(Carnet_listActivity.this, Carnet_createActivity.class);
            startActivity(carnet_createIntent);
        }
    };

    private AdapterView.OnItemClickListener item_action = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            final Carnet carnet = (Carnet) parent.getItemAtPosition(position);
            Intent page_listIntent = new Intent(Carnet_listActivity.this, Page_listActivity.class);
            Bundle extras = new Bundle();
            extras.putInt("carnet_id", carnet.getId());
            page_listIntent.putExtras(extras);
            startActivity(page_listIntent);
        }
    };

    @Override
    public void onDestroy() {
        database.close();
        super.onDestroy();
    }
}