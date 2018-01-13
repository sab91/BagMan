package fr.utt.if26.mytravel.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;

import fr.utt.if26.mytravel.Helpers.MenuHeader;
import fr.utt.if26.mytravel.Helpers.CarnetAdapter;
import fr.utt.if26.mytravel.Model.Carnet;
import fr.utt.if26.mytravel.Config.Bdd;
import fr.utt.if26.mytravel.DAO.CarnetDAO;
import fr.utt.if26.mytravel.R;

public class Carnet_listActivity extends MenuHeader {
    private Bdd database;
    private CarnetDAO cdao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carnet_list1);

        database = new Bdd(this);
        cdao = new CarnetDAO(database);

        CarnetAdapter ca = new CarnetAdapter(this, android.R.layout.activity_list_item, cdao.getList());

        GridView carnet_lv = (GridView) findViewById(R.id.carnet_list);
        carnet_lv.setAdapter(ca);
        carnet_lv.setOnItemClickListener(item_action);

        Button new_carnetButton = (Button) findViewById(R.id.new_carnetBtn);
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
            Intent carnet_itemIntent = new Intent(Carnet_listActivity.this, Carnet_itemActivity.class);
            Bundle extras = new Bundle();

            extras.putInt("id", carnet.getId());
            carnet_itemIntent.putExtras(extras);
            startActivity(carnet_itemIntent);
        }
    };

    @Override
    public void onDestroy(){
        database.close();
        super.onDestroy();
    }
}